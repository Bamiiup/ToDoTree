import React from 'react';
import RepresentationList from './../components/RepresentationList';
import {connect} from 'react-redux';
import {set, remove} from './../store/server/representation/Actions';
import {representationService} from './../appContext/Context';
import {representationListStates} from './../store/server/representation/Reducer';

class RepresentationListContainer extends React.Component {
  componentDidMount() {
    if(this.props.state === representationListStates.loaded) {
      return;
    }

    representationService.getList().then((data) => data.json()).then(representations => {

      //TODO: Add name field to representation and delete this
      representations.forEach(representation => {
        representation.name = representation.tags.map((tag => tag.name)).join(" ");
      });

      this.props.dispatch(set(representations));
    });
  }

  onClickCreate = () => {
    this.props.history.push("/representationEditor");
  }

  onClickRepresentation = (id) => {
    this.props.history.push("/representationEditor/" + id);
  }

  onClickRemove = (id) => {
    representationService.remove(id).then(() => {
      this.props.dispatch(remove(id));
    });
  }

  render() {
    return(
      <RepresentationList
        representations={this.props.representations}
        onClickCreate={this.onClickCreate}
        onClickRepresentation={this.onClickRepresentation}
        onClickRemove={this.onClickRemove}/>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    state: state.server.representation.state,
    representations: Object.values(state.server.representation.byId)
  };
};

export default connect(mapStateToProps)(RepresentationListContainer);
