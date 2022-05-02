import React, { Component, Fragment } from 'react'
import styled from 'styled-components';
import axios from 'axios';
import Navbar from '../Navbar';


const SellpageStyle = styled.div`
  width:90%;
  margin:0px auto;
  padding-top:20px;
  .sellhead{
    text-align:center;
    color: #b7c2f1;
    border-radius: .5em;
    h3,h4{
      padding:5px;
    }
  }
 
}
`;

const SellGroup = styled.div`
  @media(min-width:1024px){
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 20px;
  }
  .sellLeft{
    text-align:center;
    img{
      width:100%;
      border-radius: 1em;
    }
    
   
  }
  .sellRight{
    box-shadow: 0px 14px 80px rgb(34 35 58 / 20%);
    background-color: #AFDCEC;
    margin:0px auto;
    display:block;
    text-align:center;
    border-radius:0.5em;
    @media(min-width:1440px){
      height:fit-content;
      margin:auto 0px;
    }
    form {
      padding: 10px;
      @media(min-width:768px){
        display:grid;
        grid-template-columns:1fr 1fr;
        height:fit-content;
        align-items: center;
      }
      
    }
    .formInput{
      margin:10px 0;
      display:block;
    }
    input {
      width: 90%;
      height: 35px;
      border-radius: 0.1em;
    }
    select{
      width:80%;
    }
    .info{
      display:block;
      .Area{
        width:80%;
      }
      @media(min-width:768px){
        display:grid;
        grid-template-columns:1fr 1fr;
        
      }
    }
    
    .item{
      margin:10px 0;
    }
    textarea{
      width:90%;
    }
    .btn{
      text-align:center;
    }
    button{
      height: 45px;
      width: 192px;
      border: 0;
      border-radius: 0.5em;
      font-size: larger;
      padding: -23px; 
      background-color: #031249;
      color: #b7c2f1;
    }
    label{
      display:block;
    }
    
  }
  
`;

class Sellpage extends Component {
/*{
  "amenities": [
    "bath",
    "kitchen",
    "gym"
    ],
  "ownerId": 20000,
  "propertyAddress": {
    "city": "Istanbul",
    "number": "221D",
    "pincode": 10000,
    "street": "Mosque Road"
  },
  "propertyName": "Rosque Apts",
  "propertyType": "Apartment"
}*/
  constructor() {
    super();
    this.state = {
      propertyName: "",
      propertyType: "",
      amenities: [],
      ownerId: "",
      city: "",
      number: "",
      pincode: "",
      street: ""
    };
  }

  NewListing(event) {
    let customerId = sessionStorage.getItem("customerId");
    let propBody = {
      amenities: this.state.amenities,
      ownerId: customerId,
      propertyAddress: {
        city: this.state.city,
        number: this.state.number,
        pincode: this.state.pincode,
        street: this.state.street
      },
      propertyName: this.state.propertyName,
      propertyType: this.state.propertyType
    };
    console.log("Request Body:: "+ JSON.stringify(propBody));

    event.preventDefault();
    axios({
      method: 'post',
      url: `http://ec2-43-204-133-176.ap-south-1.compute.amazonaws.com/properties/create`,
      headers: {'Content-Type':`application/json`},
      data: {
        amenities: this.state.amenities,
        ownerId: customerId,
        propertyAddress: {
          city: this.state.city,
          number: this.state.number,
          pincode: this.state.pincode,
          street: this.state.street
        },
        propertyName: this.state.propertyName,
        propertyType: this.state.propertyType
      }
    }).then(response => {
      console.log('NewListing', JSON.stringify(response));
      let createRes = JSON.parse(JSON.stringify(response));
      if(createRes.data && createRes.data === 'Property advertised successfully'){
        window.location.assign("Listings");
      }
    }).catch(error => {
      console.log(error);
    });
  }

  handlePropNameChange(e) {
    this.setState({
      propertyName: e.target.value,
    });
  };

  handlePropTypeChange(e) {
    this.setState({
      propertyType: e.target.value,
    });
  };

  handlePropTypeChange(e) {
    this.setState({
      propertyType: e.target.value,
    });
  };

  handlePropCityChange(e) {
    this.setState({
      city: e.target.value,
    });
  };

  handlePropNumberChange(e) {
    this.setState({
      number: e.target.value,
    });
  };

  handlePropPincodeChange(e) {
    this.setState({
      pincode: e.target.value,
    });
  };

  handlePropStreetChange(e) {
    this.setState({
      street: e.target.value,
    });
  };

  handlePropAmenitiesChange(e) {
    let newAm = this.state.amenities;
    newAm.push(e.target.value);
    this.setState({
      amenities: newAm,
    });
  };


  render() {
    return (
      <Fragment>
        <Navbar />
        <SellpageStyle>
          <div className="sellhead">
            <h3>Want to sell your home?</h3>
          </div>
          <SellGroup>
            <div className="sellLeft">
              <img src={require('../../../assets/pexels-photo-955793.jpeg')} alt="sellpage" />
          </div>
            <div className="sellRight">
              <form action="post" onSubmit={this.NewListing.bind(this)}>

                <div className="formInput">
                  <label htmlFor="Property Name">Property Name:</label>
                  <input type="text" name="propertyName"  onChange={this.handlePropNameChange.bind(this)}/>
                </div>

                <div className="formInput">
                  <label htmlFor="Property Type">Property Type:</label>
                  <input type="text" name="propertyType"  onChange={this.handlePropTypeChange.bind(this)}/>
                </div>

                <div className="formInput">
                  <label htmlFor="City">City:</label>
                  <input type="text" name="city"  onChange={this.handlePropCityChange.bind(this)}/>
                </div>

                <div className="formInput">
                  <label htmlFor="House number">House number:</label>
                  <input type="text" name="number"  onChange={this.handlePropNumberChange.bind(this)}/>
                </div>

                <div className="formInput">
                  <label htmlFor="pincode">Property pincode:</label>
                  <input type="text" name="pincode"  onChange={this.handlePropPincodeChange.bind(this)}/>
                </div>

                <div className="formInput">
                  <label htmlFor="Property street">Property street:</label>
                  <input type="text" name="street"  onChange={this.handlePropStreetChange.bind(this)}/>
                </div>


                <div className="formInput">
                  <label htmlFor="amenities">Amenities:</label>
                  <input type="text" name="amenities" onChange={this.handlePropAmenitiesChange.bind(this)}/>
                </div>


                <div className='btn'> <button type="submit" >Submit</button> </div>
              </form>
            </div>
          </SellGroup>
        </SellpageStyle>
      </Fragment>
    )
  }
}

export default  Sellpage;
