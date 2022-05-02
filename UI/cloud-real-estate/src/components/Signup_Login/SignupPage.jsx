import React, { Component,Fragment } from 'react';
import styled from 'styled-components';
import Navbar from '../Navbar';
import axios from 'axios';

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

const Show = styled.div`
  .dontshow{
    display:none;
  }
`;

const FormGrid = styled.div`
  background-color: "#0dcaf0";
  background-size:cover;
  @media(min-width:768px){
    .left{
      width:420px;
      margin:0px auto;
    }
    .right{
      width:420px;
      margin:0px auto;
    }
  }
`;

const ErrorContainer = styled.div`
    height: 25px;
    align-items: center;
    text-align: center;
    color: #C04000
`;

const RenderCheckBox = styled.div`
    align-items: center;
    text-align: center;
`;

export default class SignupPage extends Component {
  constructor() {
    super();
    this.state = {
      showLogin: false,
      password: '',
      error: '',
      name:'',
      email:'',
      isRenter: false
    };
    this.handlePassChange = this.handlePassChange.bind(this);
    this.handleRePassChange = this.handleRePassChange.bind(this);
    this.handleNameChange=this.handleNameChange.bind(this);
    this.handleEmailChange=this.handleEmailChange.bind(this);
    this.handleLogInSubmit = this.handleLogInSubmit.bind(this);
    this.handleSignUpSubmit = this.handleSignUpSubmit.bind(this);
    this.dismissError = this.dismissError.bind(this);
    this.handleClick = this.handleClick.bind(this)
  }
  handleClick(){
    console.log("this.state.showLogin:: "+this.state.showLogin);
    this.setState({
      showLogin:!this.state.showLogin,
      password: '',
      error: '',
      name:'',
      email:'',
      isRenter: false
    });
  }
  dismissError() {
    this.setState({ error: '' });
  }

  handleSignUpSubmit(e) {
    console.log("isRenter:: "+ this.state.isRenter);
    e.preventDefault();

    if (!this.state.name) {
      return this.setState({ error: '* Name is required' });
    }
    if (!this.state.email) {
      return this.setState({ error: '* Email is required' });
    }
    if (!this.state.password) {
      return this.setState({ error: '* Password is required' });
    }
    if (this.state.password.length < 6) {
      return this.setState({ error: '* Password must be at least 6 characters long.' });
    }
    if (this.state.re_password !== this.state.password ) {
      return this.setState({ error: '* Password Do not Match' });
    }

    const signUpBody = {
      name: this.state.name,
      email: this.state.email,
      password: this.state.password,
      type: this.state.isRenter ? 'Renter' : 'Seller'
    };
    const headers = {
      'Content-Type': 'application/json'
    };
    axios.post('http://ec2-43-204-133-176.ap-south-1.compute.amazonaws.com/personas/signup',
      signUpBody, { headers })
      .then(response => {
          //console.log("Sign up Response:: "+ JSON.stringify(response));
          let res = JSON.parse(JSON.stringify(response));
          if(res.data && res.status === 201){
            return this.setState({
              showLogin:true,
              password: '',
              error: '',
              name:'',
              email:'',
              isRenter: false
            });
          }else{
            return this.setState({ error: 'Failed to sign Up' });
          }
        }
      );
  }

  handleLogInSubmit(e) {
    e.preventDefault();

    if (!this.state.email) {
      return this.setState({ error: '* Email is required' });
    }
    if (!this.state.password) {
      return this.setState({ error: '* Password is required' });
    }
    if (this.state.password.length < 6) {
      return this.setState({ error: '* Password must be at least 6 characters long.' });
    }

    const headers = {
      'Content-Type': 'application/json'
    };
    axios({
      method: 'post',
      url: `http://ec2-43-204-133-176.ap-south-1.compute.amazonaws.com/personas/login`,
      headers: headers,
      data: {
        email: this.state.email,
        password: this.state.password
      }
    }).then((response) => {
      console.log("Login Response:: "+ JSON.stringify(response.data));
      let loginRes = JSON.parse(JSON.stringify(response.data));
      if(loginRes && loginRes.customerId){
        window.sessionStorage.setItem("customerId", loginRes.customerId);
        window.location.assign("Listings");
      }else{
        return this.setState({ error: 'Failed to login, Try Again' });
      }
    });
  }
  handleNameChange(e) {
    this.setState({
      name: e.target.value,
    });
  };
  handleEmailChange(e) {
    this.setState({
      email: e.target.value,
    });
  };

  handlePassChange(e) {
    this.setState({
      password: e.target.value,
    });
  }
  handleRePassChange(e) {
    this.setState({
      re_password: e.target.value,
    });
  }

  render() {
    return <Fragment>
      <Navbar />
      <FormGrid>
      {this.state.showLogin === false &&
        <div className="left">
        <Form onSubmit={this.handleSignUpSubmit}>
          <h4>Sign Up</h4>
          {this.state.error && <ErrorContainer onClick={this.dismissError}>{this.state.error}</ErrorContainer>}
          <div className="sign">
            <label htmlFor="Name">Name</label>
            <input type="text" name="Name" value={this.state.name} onChange={this.handleNameChange} />
          </div>
          <div className="sign">
            <label htmlFor="Email">Email</label>
            <input type="email" name="Email" value={this.state.email} onChange={this.handleEmailChange} />
          </div>
          <div className="sign">
            <label htmlFor="Password">Password</label>
            <input type="password" name="Password" value={this.state.password} onChange={this.handlePassChange} />
          </div>
          <div className="sign">
            <label htmlFor="Password">Retype-Password</label>
            <input type="password" name="Password" value={this.state.re_password} onChange={this.handleRePassChange} />
          </div>
          <RenderCheckBox>
            <input
              type="checkbox"
              id="topping"
              name="topping"
              value={this.state.isRenter}
              checked={this.state.isRenter}
              onChange={()=> this.setState({isRenter: !this.state.isRenter})}
            />
            Is Renter?
          </RenderCheckBox>
          <div className="btn">
            <input type="submit" value="Sign Up" />
          </div>
          <div className="signUpLink">
            <a onClick={this.handleClick.bind(this)}>
              Have An Account? Log In
            </a>
          </div>
        </Form>
      </div>}
      {this.state.showLogin === true &&
      <div className="right">
        <Show>
          <div className={this.state.showLogin ? "show" : "dontshow"}>
            <Form onSubmit={this.handleLogInSubmit}>
              <h4>Log In</h4>
              {this.state.error && <ErrorContainer onClick={this.dismissError}>{this.state.error}</ErrorContainer>}
              <div className="sign">
                <label htmlFor="Email">Email</label>
                <input type="text" name="Email"  value={this.state.email} onChange={this.handleEmailChange} />
              </div>
              <div className="sign">
                <label htmlFor="Password">Password</label>
                <input type="password" name="Password" value={this.state.password} onChange={this.handlePassChange} />
              </div>
              <div className="btn">
                <input type="submit" value="Log In" />
              </div>
              <div className="signUpLink">
                <a onClick={this.handleClick.bind(this)}>
                  Don't have an Account? Sign up
                </a>
              </div>
            </Form>
          </div>
        </Show>
      </div>}
      </FormGrid>
      </Fragment>;
  }
}
