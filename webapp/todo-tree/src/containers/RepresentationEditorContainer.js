import React from 'react';
import RepresentationEditor from './../components/RepresentationEditor';
import {connect} from 'react-redux';
import {update, set} from './../store/ui/representationEditor/Actions';
import {representationService} from './../appContext/Context';

class RepresentationEditorContainer extends React.Component {
  initialize() {
    let id = this.props.match.params.id;

    if(id) {
      representationService.get(id).then(data => {
        return data.json();
      }).then(representation => {
        this.props.dispatch(set({
          ...representation,
          tags: representation.tags.map(tag => tag.name).join(",")
        }));
      });
    } else {
      this.props.dispatch(set({}));
    }
  }

  componentDidMount() {
    this.initialize();
  }

  componentDidUpdate(prevProps, prevState) {
    if(prevProps.match.params.id !== this.props.match.params.id) {
      this.initialize();
    }
  }

  parseInt(line) {
    if(!line) {
      return null;
    }

    return parseInt(line, 10);
  }

  onChangeEndAfterDays = (event) => {
    let dayAmountAfterToday = this.parseInt(event.target.value);
    this.props.dispatch(update({
      dayAmountAfterToday
    }));
  }

  onChangeTags = (event) => {
    this.props.dispatch(update({
      tags: event.target.value
    }));
  }

  onChangePriorityFrom = (event) => {
    let priority = event.target.value === "" ? null : event.target.value;
    this.props.dispatch(update({
      bottomPriority: priority
    }));
  }

  onChangePriorityTo = (event) => {
    let priority = event.target.value === "" ? null : event.target.value;
    this.props.dispatch(update({
      topPriority: priority
    }));
  }

  onChangeImportant = (event) => {
    let isImportant;
    if(event.target.value === "no") {
      isImportant = false;
    }

    if(event.target.value === "yes") {
      isImportant = true;
    }

    if(event.target.value === "") {
      isImportant = null;
    }

    this.props.dispatch(update({
      isImportant
    }));
  }

  onChangeWeightFrom = (event) => {
    let bottomWeight = this.parseInt(event.target.value);
    this.props.dispatch(update({
      bottomWeight
    }));
  }

  onChangeWeightTo = (event) => {
    let topWeight = this.parseInt(event.target.value);
    this.props.dispatch(update({
      topWeight
    }));
  }

  getPlainRepresentation() {
    return {
      ...this.props.representation,
      tags: this.props.representation.tags.split(" ").map(tagName => ({name: tagName}) )
    }
  }

  onClickSave = () => {
    if(this.props.match.params.id) {
      representationService.update(this.getPlainRepresentation()).then(() => {
        this.props.history.goBack();
      });
    } else {
      representationService.create(this.getPlainRepresentation()).then(() => {
        this.props.history.goBack();
      });
    }
  }

  render() {
    return(
      <RepresentationEditor {...this.props.representation}
        onChangeEndAfterDays={this.onChangeEndAfterDays}
        onChangeTags={this.onChangeTags}
        onChangePriorityTo={this.onChangePriorityTo}
        onChangePriorityFrom={this.onChangePriorityFrom}
        onChangeImportant={this.onChangeImportant}
        onChangeWeightTo={this.onChangeWeightTo}
        onChangeWeightFrom={this.onChangeWeightFrom}
        onClickSave={this.onClickSave}/>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    representation: state.ui.representationEditor
  };
}

export default connect(mapStateToProps)(RepresentationEditorContainer);
