import React, { Component,useState, Fragment, useEffect } from 'react';
import ListItem from './ListItem';
import NewItem from './NewItem';
import UpdateItem from './UpdateItem';
import { getTodos, addTodo, deleteTodo, updateTodo } from "./api/TodoApi";
import _ from "lodash";

const TodoList = () => {
  const [inputValue, setInputValue] = useState("");
  const [list, setList] = useState(null);
  const [error, setError] = useState("");

  const handleLoadTasks = () => {
    getTodos()
      .then((response) => {
        setList(response);
      })
      .catch((error) => {
        setError("Unable to retrieve todo's");
      });
  };

  const handleAddTask = (inputV) => {
    if (inputV === "") return;

    const newTask = {
      id: _.parseInt(list.length ? list[list.length - 1].id : 0) + 1,
      content: inputV,
    };

    addTodo(newTask).then(() => {
      setList([...list, newTask]);
      setInputValue("");
    });
  };

  const handleDeleteTask = (id) =>
    deleteTodo(id).then(() => {
      setList(list.filter((item) => item.id !== id));
    });

    const handleUpdateTask = (updateV) => {
      if (updateV.content === "") return;
  
      updateTodo(updateV).then((response) => {
        setList(
          list.map((x) => (x.id === response.id ? { ...x, ...response } : x))
        );
      });
    };
  useEffect(() => {
    handleLoadTasks();
  }, []);

  /*useEffect(() => {
    setList([{"id":1,"content":"Restful API homework","updatedAt":"2020-04-05 00:00:00"}])
  }, []);*/

  if (list === null) {
    return <div>Tasks is loading ...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }


  return(
             <div>
                {
                    list.map(item=>(
                        <div>
                            <ListItem item={item}/>
                            <UpdateItem 
                            updateItem={handleUpdateTask}
                            item={item} 
                            index={item.id}
                            />
                            <button onClick={handleDeleteTask.bind(this,item.id)}>Delete</button>
                        </div>
                    ))
                }
                <NewItem addItem={handleAddTask}/>
            </div>
        );
}

export default TodoList;