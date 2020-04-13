import React, { Component } from 'react';
import './ListItem.css';
 
class ListItem extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const item=this.props.item
        return <p className="item">{item.content}</p>
    }
}

export default ListItem;