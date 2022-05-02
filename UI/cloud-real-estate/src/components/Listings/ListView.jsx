import React, { Fragment, Component } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import Navbar from '../Navbar';
import Loader from '../../../assets/loader.gif';

const Form = styled.form`
  box-shadow: 0px 14px 80px rgb(34 35 58 / 20%);
  width:90%;
  height:50%;
  background-color: #AFDCEC;
  margin:0px auto;
  border-radius: 1em;
  h4{
    text-align:center;
    padding 10px;
  }
  .sign{
    margin:20px 0;
    label,input{
      display:block;
      margin: 0px auto;
      width: 80%;
    }
    input{  
      border-radius: 0.2em;
      height:40px;
      padding-left: 5px;
    }
  }
  .btn {
    text-align: center;
    padding: 20px;
    border-radius: 2em;
  }
  .btn input{
    height: 45px;
    width: 192px;
    border: 0;
    border-radius: 0.5em;
    font-size: larger;
    padding: -23px; 
    background-color: #43C6DB;
    color: #033E3E;
  }
  .signUpLink{
    align-items:center;
    text-align:center;
    margin-bottom:20px;
    padding-bottom:20px;
    color: #293064;
    cursor: pointer;
  }
`;
const Liststyle = styled.div`
  align-items: center;
  justify-items: center;
  width:90%;
  padding-top:120px;
  margin:0px auto;
  img{
    width:100%;
  }
  .viewright{
    text-align:justified;
    h5{
      background-color:#b7c2f1;
      padding:20px;
    }
  }
  .btn{
    text-align:center;
  }
  input[type="button"] {
    height: 45px;
    width: 192px;
    border: 0;
    border-radius: 0.5em;
    font-size: larger;
    padding: -23px;
    background-color: #031249;
    color: #b7c2f1;
  }
  .Image {
    width: 25%;
    margin: 0px auto;
    position: relative;
    left: 37%;
  }
`;

const AgentCard = styled.div`
  width: 300px;
  height: 200px;
  margin-bottom: 10px;
  padding-top: 20px;
  padding-left: 20px;
  padding-right: 20px;
  box-shadow: 0px 14px 80px rgb(34 35 58 / 20%);
  color: #000000;
  align-items: flex-start;
  justify-items: flex-start;
  background-color: #ffffff
`;

const RenderCheckBox = styled.div`
    align-items: center;
    text-align: center;
`;

const ErrorContainer = styled.div`
    height: 25px;
    align-items: center;
    text-align: center;
    color: #C04000
`;

const SuccessContainer = styled.div`
    height: 25px;
    align-items: center;
    text-align: center;
    color: green
`;


class ListView extends Component {
  constructor() {
    super();
    this.state = {
      ready: 'initial',
      agentList: [],
      selectedAgentId: null,
      success: ''
    };
    this.handleAgentChange = this.handleAgentChange.bind(this);
    this.handleBookPropertySubmit = this.handleBookPropertySubmit.bind(this);
    this.dismissError = this.dismissError.bind(this);
  }

  componentDidMount() {
    this.setState({ ready: 'loading', selectedAgentId: null, agentList:[], success: ''});
    const headers = {
      'Content-Type': 'application/json'
    };
    axios({
      method: 'get',
      url: `http://ec2-43-204-133-176.ap-south-1.compute.amazonaws.com/personas/agents`,
      headers: headers
    }).then((response) => {
      let agentList  = JSON.parse(JSON.stringify(response));
      console.log('Agent Listings::: '+ JSON.stringify(agentList.data));
      this.setState({
        ready: 'loaded',
        agentList: agentList.data,
      })
    });
  }

  handleAgentChange(e) {
    console.log("handleAgentChange "+ e.target.value);
    this.setState({
      selectedAgentId: e.target.value,
    });
  }

  dismissError() {
    this.setState({ error: '', success: '' });
  }

  handleBookPropertySubmit() {
    console.log("handleBookPropertySubmit ");
    let customerId = sessionStorage.getItem("customerId");
    let propertyId = sessionStorage.getItem("propertyId");
    const bookPropertyBody = {
      propertyId: propertyId,
      customerId: customerId,
      agentId: this.state.selectedAgentId,
    };
    console.log("bookPropertyBody::  "+JSON.stringify(bookPropertyBody));
    const headers = {
      'Content-Type': 'application/json'
    };
    axios.post('http://ec2-43-204-133-176.ap-south-1.compute.amazonaws.com/booking/create',
      bookPropertyBody, { headers })
      .then(response => {
          console.log("Booking Response:: "+ JSON.stringify(response));
          let res = JSON.parse(JSON.stringify(response));
          if(res.data && res.status === 201){
            return this.setState({ success: res.data });
          }else{
            return this.setState({ error: 'Failed to sign Up' });
          }
        }
      );

  }

  render() {
    const { agentList, ready } = this.state;
    return (
      <Fragment>
        <Navbar />
        <div>
          {this.state.success && <SuccessContainer onClick={this.dismissError}>{this.state.success}</SuccessContainer>}
          <div className="btn" onClick={this.handleBookPropertySubmit}>
            <input type="submit" value="Book Property" />
          </div>
          <Liststyle>
            {ready === 'loading' ? (<img src={Loader} className='Image' alt="loader"/>) : ''}
            {ready === 'loaded' && (
              agentList.map((agent, index)=>{
                return <AgentCard key={agent.id.toString()}>
                  <RenderCheckBox>
                    <h6>{agent.name}</h6>
                    <h6>{agent.email}</h6>
                    <input
                      type="checkbox"
                      id="topping"
                      name="topping"
                      value={agent.id}
                      checked={this.state.selectedAgentId && this.state.selectedAgentId == agent.id}
                      onChange={this.handleAgentChange}
                    />
                    Is Agent Selected?
                  </RenderCheckBox>
                </AgentCard>
              })
            )}

          </Liststyle>
        </div>

      </Fragment>
    );
  }
}

export default ListView;
