import React, { Component } from 'react';
import './ListItem.css';
 
class ListItem extends Component{
    constructor(props){
        super(props);
        this.state={
            inputContent:''
        }
    }
    OnInputChange=(event)=>{
        this.setState({
            inputContent:event.target.value
        })
        console.log(this.state.inputContent)
    }

    onUpdateButtonClick=()=>{
        const newTask = {
            id: this.props.index,
            content: this.state.inputContent,
          };
          console.log(newTask)
          this.props.updateItem(newTask)
        this.setState({
            inputContent:''
        })
    }
    onDeleteButtonClick=()=>{
        this.props.deleteItem(this.props.index)
    }
    render(){
        const item=this.props.item
        return(
            <div className="back" alignItems= 'center' justifyContent='center'>
                <p className="item">{item.content}</p>
                <input className="update-input" value={this.state.inputContent} onChange={this.OnInputChange}/>
                <button className="update-button" onClick={this.onUpdateButtonClick}>Update</button>
                <button className="delete-button" onClick={this.onDeleteButtonClick}>Delete</button>
            </div>
        );
    }
}

export default ListItem;