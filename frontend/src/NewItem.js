import React, { Component } from 'react';

class NewItem extends Component{
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
        this.props.addItem(this.state.inputContent)
        this.setState({
            inputContent:''
        })
    }

    render(){
        return(
            <div className="back-new">
                <input className="add-input" value={this.state.inputContent} onChange={this.OnInputChange}/>
                <button className="add-button" onClick={this.onAddButtonClick}>Add</button>
            </div>
        );
    }
}

export default NewItem;