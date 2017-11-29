import React from 'react';
import RepresentationEditor from './../components/RepresentationEditor';
import {connect} from 'react-redux';
import {update as updateRepresentationEditor, set as setRepresentationEditor} from './../store/ui/representationEditor/Actions';
import {update as updateRepresentation, set as setRepresentation} from './../store/server/representation/Actions';
import {tabs, sortFields} from './../store/ui/representationEditor/Reducer';
import {representationService} from './../appContext/Context';
import RepresentationEditorUtils from './../utils/RepresentationEditorUtils';

class RepresentationEditorContainer extends React.Component {
  initialize() {
    let id = this.props.match.params.id;

    if(id) {
      representationService.get(id).then(data => {
        return data.json();
      }).then(representation => {
        this.props.dispatch(setRepresentationEditor({
          ...representation,
          tags: representation.tags.map(tag => tag.name).join(",")
        }));
      });
    } else {
      this.props.dispatch(setRepresentationEditor({}));
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

  onChangeName = (event) => {
    this.props.dispatch(updateRepresentationEditor({
      name: event.target.value
    }));
  }

  onChangeEndAfterDays = (event) => {
    let dayAmountAfterToday = this.parseInt(event.target.value);
    this.props.dispatch(updateRepresentationEditor({
      dayAmountAfterToday
    }));
  }

  onChangeTags = (event) => {
    this.props.dispatch(updateRepresentationEditor({
      tags: event.target.value
    }));
  }

  onChangePriorityFrom = (event) => {
    let priority = event.target.value === "" ? null : event.target.value;
    this.props.dispatch(updateRepresentationEditor({
      bottomPriority: priority
    }));
  }

  onChangePriorityTo = (event) => {
    let priority = event.target.value === "" ? null : event.target.value;
    this.props.dispatch(updateRepresentationEditor({
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

    this.props.dispatch(updateRepresentationEditor({
      isImportant
    }));
  }

  onChangeWeightFrom = (event) => {
    let bottomWeight = this.parseInt(event.target.value);
    this.props.dispatch(updateRepresentationEditor({
      bottomWeight
    }));
  }

  onChangeWeightTo = (event) => {
    let topWeight = this.parseInt(event.target.value);
    this.props.dispatch(updateRepresentationEditor({
      topWeight
    }));
  }

  onClickFilter = () => {
    this.props.dispatch(updateRepresentationEditor({
      activatedTab: tabs.filter
    }));
  }

  onClickSort = () => {
    this.props.dispatch(updateRepresentationEditor({
      activatedTab: tabs.sort
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
      representationService.update(this.getPlainRepresentation())
        .then((data) => data.json()).then((representation) => {

          this.props.dispatch(updateRepresentation(representation));
          this.props.history.goBack();
      });
    } else {
      representationService.create(this.getPlainRepresentation())
        .then((data) => data.json()).then((representation) => {

          this.props.dispatch(setRepresentation([representation]));
          this.props.history.goBack();
      });
    }
  }

  onClickNew = () => {
    const oldSortRules = this.props.representation.sortRules;
    let newSortRules = [...oldSortRules];
    const firstNotUsedField = RepresentationEditorUtils.findFirstNotUsedSortField(oldSortRules);

    if(firstNotUsedField === undefined) {
      //TODO: replace this by some meaning message for user
      return;
    }

    newSortRules.push({
      fieldName: firstNotUsedField,
      order: oldSortRules.length,
      isDirect: true
    });

    this.props.dispatch(updateRepresentationEditor({sortRules: newSortRules}));
  }

  onClickRemoveSortRule = (fieldName) => {
    this.props.dispatch(updateRepresentationEditor({
      sortRules: this.props.representation.sortRules.filter(sortRule => {
        return sortRule.fieldName !== fieldName;
      })
    }));
  }

  onClickSortDirection = (fieldName) => {
    this.props.dispatch(updateRepresentationEditor({
      sortRules: this.props.representation.sortRules.map(sortRule => {
        if(sortRule.fieldName !== fieldName) {
          return sortRule;
        } else {
          return {
            ...sortRule,
            isDirect: !sortRule.isDirect
          };
        }
      })
    }));
  }

  onChangeFieldName = (prevFieldName, nextFieldName) => {
    this.props.dispatch(updateRepresentationEditor({
      sortRules: this.props.representation.sortRules.map(sortRule => {
        if(sortRule.fieldName !== prevFieldName) {
          return sortRule;
        } else {
          return {
            ...sortRule,
            fieldName: nextFieldName
          };
        }
      })
    }));
  }

  render() {
    return(
      <RepresentationEditor {...this.props.representation}
        onChangeName={this.onChangeName}
        onChangeEndAfterDays={this.onChangeEndAfterDays}
        onChangeTags={this.onChangeTags}
        onChangePriorityTo={this.onChangePriorityTo}
        onChangePriorityFrom={this.onChangePriorityFrom}
        onChangeImportant={this.onChangeImportant}
        onChangeWeightTo={this.onChangeWeightTo}
        onChangeWeightFrom={this.onChangeWeightFrom}
        onClickSave={this.onClickSave}
        onClickSort={this.onClickSort}
        onClickFilter={this.onClickFilter}
        onClickNew={this.onClickNew}
        onClickRemoveSortRule={this.onClickRemoveSortRule}
        onClickSortDirection={this.onClickSortDirection}
        onChangeFieldName={this.onChangeFieldName}/>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    representation: state.ui.representationEditor
  };
}

export default connect(mapStateToProps)(RepresentationEditorContainer);
