import React from 'react';
import TodoEditor from './../components/TodoEditor';
import {todoService} from "./../appContext/Context";
import DateUtils from './../utils/DateUtils';

/*
  TODO: 1. move state to store
  TODO: 2. handle error from todoService.get
  TODO: 3. handle error from todoService.create
  TODO: 4. handle error from todoService.update
*/
export default class TodoEditorContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      parentId: this.props.parentId,
      name: "",
      comment: "",
      startDate: DateUtils.formatToHtmlDateInput(new Date()),
      endDate: DateUtils.formatToHtmlDateInput(new Date()),
      tags: "",
      priority: "medium",
      important: false,
      weight: 1
    };
  }

  componentDidMount() {
    if(this.props.mode === "edit") {
      todoService.get(this.props.id).then((data) => {
        return data.json();
      }).then(todo => {
        let result = Object.assign({}, todo, {
          startDate: DateUtils.formatToHtmlDateInput(new Date(todo.startDate)),
          endDate: DateUtils.formatToHtmlDateInput(new Date(todo.endDate)),
          tags: todo.tags.map(tag => (tag.name)).join(",")
        });

        this.setState(result);
      });
    }
  }

  onChangeName = (event) => {
    this.setState({
      name: event.target.value
    });
  }

  onChangeComment = (event) => {
    this.setState({
      comment: event.target.value
    });
  }

  onChangeStartDate = (event) => {
    this.setState({
      startDate: event.target.value
    });
  }

  onChangeEndDate = (event) => {
    this.setState({
      endDate: event.target.value
    });
  }

  onChangeTags = (event) => {
    this.setState({
      tags: event.target.value
    });
  }

  onChangePriority = (event) => {
    this.setState({
      priority: event.target.value
    });
  }

  onChangeImportant = (event) => {
    this.setState({
      important: (event.target.value === "yes")
    });
  }

  onChangeWeight = (event) => {
    this.setState({
      weight: event.target.value
    });
  }

  _getFormattedId() {
    if(!this.props.id) {
      return null;
    }
    return parseInt(this.props.id, 10);
  }

  _getFormattedTodoToService() {
    let todo = {
      id: this._getFormattedId(),
      name: this.state.name,
      comment: this.state.comment,
      startDate: DateUtils.formatToDate(this.state.startDate),
      endDate: DateUtils.formatToDate(this.state.endDate),
      tags: this.state.tags.split(",").map(tag => ({name: tag.trim()})),
      priority: this.state.priority,
      important: this.state.important,
      weight: this.state.weight
    };
    return todo;
  }

  onClickSave = () => {
    let todo = this._getFormattedTodoToService();
    if(this.props.mode === "new") {
      todoService.create(todo).then(() => {
        this.props.history.goBack();
      });
    }

    if(this.props.mode === "edit") {
      todoService.update(todo).then(() => {
        this.props.history.goBack();
      });
    }
  }

  render() {
    return(
      <TodoEditor
        {...this.state}
        onChangeName={this.onChangeName}
        onChangeComment={this.onChangeComment}
        onChangeStartDate={this.onChangeStartDate}
        onChangeEndDate={this.onChangeEndDate}
        onChangeTags={this.onChangeTags}
        onChangePriority={this.onChangePriority}
        onChangeImportant={this.onChangeImportant}
        onChangeWeight={this.onChangeWeight}
        onClickSave={this.onClickSave}
        />
    );
  }
}
