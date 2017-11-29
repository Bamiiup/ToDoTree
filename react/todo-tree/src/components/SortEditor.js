import React from 'react';
import {sortFields} from './../store/ui/representationEditor/Reducer';
import RepresentationEditorUtils from './../utils/RepresentationEditorUtils';

class SortEditorItem extends React.Component{
  getDisplayName(sortField) {
    if(sortField === sortFields.priority) {
      return "Priority";
    }

    if(sortField === sortFields.weight) {
      return "Weight";
    }

    if(sortField === sortFields.important) {
      return "Important";
    }

    if(sortField === sortFields.startDate) {
      return "Start Date";
    }

    if(sortField === sortFields.endDate) {
      return "End Date";
    }

    if(sortField === sortFields.name) {
      return "Name";
    }
  }

  getSortDirectionCssClass(isDirect) {
    if(isDirect) {
      return "oi oi-sort-ascending";
    } else {
      return "oi oi-sort-descending";
    }
  }

  onClickRemoveSortRule = () => {
    this.props.onClickRemoveSortRule(this.props.sortRule.fieldName);
  }

  onClickSortDirection = () => {
    this.props.onClickSortDirection(this.props.sortRule.fieldName);
  }

  onChangeFieldName = (event) => {
    this.props.onChangeFieldName(this.props.sortRule.fieldName, event.target.value);
  }

  render() {
    const sortRule = this.props.sortRule;
    return(
      <div
        style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}
        className="mb-1">
        <div style={{cursor: 'pointer'}} className="mr-2"
          onClick={this.onClickSortDirection}>
          <span
            className={this.getSortDirectionCssClass(sortRule.isDirect)}
            style={{color: 'rgb(100, 100, 100)'}} />
        </div>
        <div style={{flexGrow: 1}}>
          <select className="form-control"
            value={sortRule.fieldName}
            onChange={this.onChangeFieldName}>

            <option value={sortRule.fieldName} key={sortRule.fieldName}>{this.getDisplayName(sortRule.fieldName)}</option>
            {
              this.props.notUsedSortFields.map(notUsedSortField => {
                return(
                  <option value={notUsedSortField} key={notUsedSortField}>{this.getDisplayName(notUsedSortField)}</option>
                );
              })
            }
          </select>
        </div>
        <div style={{cursor: 'pointer'}} className="ml-3"
          onClick={this.onClickRemoveSortRule}>
          <span
            className="oi oi-trash"
            style={{color: 'rgb(201, 137, 137)'}} />
        </div>
      </div>
    );
  }
}

export default class SortEditor extends React.Component {
  render() {
    const notUsedSortFields = RepresentationEditorUtils.findAllNotUsedSortFields(this.props.sortRules);
    return(
      <div className="row" style={{margin: 4}}>
        <div className="col-2">
          <button
            type="button"
            className="btn btn-light btn-sm"
            onClick={this.props.onClickNew}>
            <span
              className="oi oi-plus"
              style={{color: 'rgb(136, 165, 134)', fontSize: '10pt'}} />
            New
          </button>
        </div>
        <div className="col-8">

          {this.props.sortRules.map(sortRule => {
            return(
              <SortEditorItem key={sortRule.fieldName}
                sortRule={sortRule}
                notUsedSortFields={notUsedSortFields}
                onChangeFieldName={this.props.onChangeFieldName}
                onClickSortDirection={this.props.onClickSortDirection}
                onClickRemoveSortRule={this.props.onClickRemoveSortRule}/>
            );
          })}

        </div>
      </div>
    );
  }
}
