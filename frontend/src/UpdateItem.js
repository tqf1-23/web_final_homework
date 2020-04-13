import React, { Component } from 'react';

class UpdateItem extends Component{
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

    onAddButtonClick=()=>{
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

    render(){
        return(
            <div>
                <input value={this.state.inputContent} onChange={this.OnInputChange}/>
                <button onClick={this.onAddButtonClick}>Update</button>
            </div>
        );
    }

}
export default UpdateItem;