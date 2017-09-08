import React from 'react';
import TodoEditor from './../components/TodoEditor';
import {dateUtils, todoService} from "./../appContext/Context";

export default class TodoEditorContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      parentId: this.props.parentId,
      name: "",
      comment: "",
      startDate: dateUtils.formatToHtmlDateInput(new Date()),
      endDate: dateUtils.formatToHtmlDateInput(new Date()),
      tags: "",
      priority: "medium",
      important: false,
      weight: 1
    };
  }

  componentDidMount() {
    if(this.props.mode === "edit") {
      // todoService.get(this.props.id).then((json) => {
      //   return json.parse();
      // }).then(data => {
      //
      // });
      let todo = {
        "id":this.props.id,
        "parentId":2,
        "name":"Edit Todo",
        "comment":"Comment",
        "startDate":1504642586430,
        "endDate":1504642586430,
        "priority":"high",
        "important":true,
        "weight":1000,
        "userId":1,
        "tags":[
          {
            "id":3,
            "name":"Super tag",
            "todoIds":[2,this.props.i]
          }
        ]
      };

      setTimeout(() => {

        let result = Object.assign({}, todo, {
          startDate: dateUtils.formatToHtmlDateInput(new Date(todo.startDate)),
          endDate: dateUtils.formatToHtmlDateInput(new Date(todo.endDate)),
          tags: todo.tags.map(tag => (tag.name)).join(",")
        });

        this.setState(result);
      }, 500);
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
      important: event.target.value
    });
  }

  onChangeWeight = (event) => {
    this.setState({
      weight: event.target.value
    });
  }

  _getFormattedTodoToService() {
    let todo = {
      name: this.state.name,
      comment: this.state.comment,
      startDate: dateUtils.formatToDate(this.state.startDate),
      endDate: dateUtils.formatToDate(this.state.endDate),
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
      // todoService.update(todo).then(() => {
      //   this.props.history.goBack();
      // });
      setTimeout(() => {
        this.props.history.goBack();
      }, 500);
    }
  }

  render() {
    return(
      <TodoEditor
        name={this.state.name}
        onChangeName={this.onChangeName}
        comment={this.state.comment}
        onChangeComment={this.onChangeComment}
        startDate={this.state.startDate}
        onChangeStartDate={this.onChangeStartDate}
        endDate={this.state.endDate}
        onChangeEndDate={this.onChangeEndDate}
        tags={this.state.tags}
        onChangeTags={this.onChangeTags}
        priority={this.state.priority}
        onChangePriority={this.onChangePriority}
        important={this.state.important}
        onChangeImportant={this.onChangeImportant}
        weight={this.state.weight}
        onChangeWeight={this.onChangeWeight}
        onClickSave={this.onClickSave}
        />
    );
  }
}
