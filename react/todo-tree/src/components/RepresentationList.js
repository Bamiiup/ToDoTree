import React from 'react';

class RepresentationItem extends React.Component {
  onClickRepresentation = () => {
    this.props.onClickRepresentation(this.props.id);
  }

  onClickRemove = () => {
    this.props.onClickRemove(this.props.id);
  }

  render() {
    return(
      <div className="row" style={{margin: 4}}>
        <div className="col-4" />
        <div
          className="col-4"
          style={{borderRadius: 5, border: '2px solid rgb(227, 230, 234)', padding: 4, fontWeight: 'bold'}}>
          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>

            <div style={{cursor: 'pointer'}}
              onClick={this.onClickRepresentation}>
              {this.props.name}
            </div>
            <div style={{cursor: 'pointer'}}
              onClick={this.onClickRemove}>
              <span
                className="oi oi-trash"
                style={{color: 'rgb(201, 137, 137)'}} />
            </div>

          </div>
        </div>
      </div>
    );
  }
}

export default class RepresentationList extends React.Component {
  render() {
    return(
      <div
        className="container"
        style={{marginTop: 60}}>

        {this.props.representations.map(representation => {
          return(
            <RepresentationItem key={representation.id}
              {...representation}
              onClickRepresentation={this.props.onClickRepresentation}
              onClickRemove={this.props.onClickRemove}
              />
          );
        })}


        <div className="row" style={{margin: 4}}>
          <div
            className="col-8"
            style={{display: 'flex', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center', margin: 4}}>
            <button type="button" className="btn" onClick={this.props.onClickCreate}>
              Создать
            </button>
          </div>
        </div>
      </div>
    );
  }
}
