import React from 'react';
import TodoEditorContainer from './../containers/TodoEditorContainer';

export default class TodoEditorPage extends React.Component {
  getMode() {
    if(this.props.match.path === "/todoEditor/edit/:id") {
      return "edit";
    }

    if(this.props.match.path === "/todoEditor/new/:parentId?") {
      return "new";
    }

    throw new Error("Unknown url!");
  }


  render() {
    return(
      <TodoEditorContainer
        history={this.props.history}
        mode={this.getMode()}
        id={this.props.match.params.id}
        parentId={this.props.match.params.parentId}/>
    );
  }
}
