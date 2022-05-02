import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Navbar from '../Navbar';
import styled from 'styled-components';
import ListItems from './ListItems';
import axios from 'axios';
import Loader from '../../../assets/loader.gif';

const List= styled.div`
  padding:50px 0;
  .listgroup{
    width:90%;
    margin: 0px auto;
  }
  .listLeft{
    box-shadow: 0px 14px 80px rgb(34 35 58 / 20%);
    text-align:center;
    @media (min-width: 1024px){
      height: 215px;
    }
  }
  input{
    width: 239px;
    height: 30px;
    margin-top: 20px;
    margin-bottom: 20px;
    padding:10px;
    @media (min-width:425px){
      width: 315px;
    }
    @media (min-width: 768px){
      width: 425px;
    }
    @media (min-width: 1024px){
      width: 280px;
    }
  }

  select{
    border-radius: 0.4em;
    width: 239px;
    height: 30px;
    border: 0;
    margin-top: 20px;
    margin-bottom: 20px;
    @media (min-width:425px){
      width: 315px;
    }
    @media (min-width: 768px){
    width: 425px;
    }
    @media (min-width: 1024px){
      width: 280px;
    }
  }
  .propt_btn{
    height: 45px;
    width: 192px;
    border: 0;
    border-radius: 0.5em;
    font-size: larger;
    padding: -23px; 
    background-color: #43C6DB;
    color: #033E3E;
  }

  @media (min-width: 1024px){
    .listgroup{
      display:grid;
      grid-template-columns:1fr 3fr;
      grid-gap: 20px;
    }
    
    .listLeft{
      height:auto;
      width:300px;
      padding:15px 0;
      background-color: #AFDCEC;
      border-radius: 0.4em;
      border-bottom-left-radius: 0.4em;
      @media (min-width: 1024px){
        height:215px;
      }
    }
    
    .listRight{
      height:auto;
    }
    
  }
  
`;
const ListRight = styled.div`
  @media (min-width: 768px){  
    .loader-img{
        width: 345px;
        margin: 0px auto;
    }
  }
  .right{
    @media (min-width: 768px){
      display:grid;
      grid-template-columns:1fr 1fr;
      
    }
    @media (min-width: 1440px){
      display:grid;
      grid-template-columns:1fr 1fr 1fr;
    }
  }
  .Image {
    width: 100%;
    margin: 0px auto;
    }
  }
  

`;

const Info = styled.div`
  @media (min-width: 375px){
    display:grid;
    grid-template-columns:1fr 1fr;
    text-align:center;
  }
`;

class Listing extends Component {

  constructor() {
    super();
    this.state = {
      lists: [],
      ready: 'initial',
      search: "",
    };
  }
    componentDidMount() {
      this.setState({
        ready: 'loading',
      });
      const headers = {
        'Content-Type': 'application/json'
      };
      axios({
        method: 'get',
        url: `http://ec2-43-204-133-176.ap-south-1.compute.amazonaws.com/properties/listings`,
        headers: headers
      }).then((response) => {
        let housingList  = JSON.parse(JSON.stringify(response));
        console.log('House Listings::: '+ JSON.stringify(housingList.data));
        this.setState({
          ready: 'loaded',
          lists: housingList.data,
        })
      })
    }
    locationChange(e){
      this.setState({
        search:e.target.value
      })
    }
    PropertyChange(e){
      this.setState({
        search:e.target.value
      })
    }

  render() {
    const { lists, ready,search } = this.state;
    const filtered = lists.filter(list => {
      return list.propertyName.toLowerCase().indexOf(search.toLowerCase()) !== -1;
    });


    return (
      <div>
        <Navbar />
        <List>
          <div className="listgroup">
            <div className="listLeft">
              <h3>Filter</h3>
              <form>
                <input type="search" name="search" placeholder='Location Name' onChange={this.locationChange.bind(this)} />
              </form>
            </div>
            <ListRight>
              <div className="loader">
                {lists.length ? '' : (<h3>There are no list items</h3>)}
                {ready === 'loading' ? (<div className='loader-img'><img src={Loader} className='Image' alt="loader" /></div>) : ''}
              </div>
              <div className="right">
                {filtered.map(listItem => (
                  <div key={listItem.propertyId} onClick={() => window.sessionStorage.setItem("propertyId", listItem.propertyId)}>
                    <Link to={`/Listview/${listItem.propertyId}`}>
                      <ListItems image={'https://i.pinimg.com/736x/6b/60/a1/6b60a17968faa55c574073f56c9065dd.jpg'} >
                        {/*<h4>&#8377;100000</h4>*/}
                        <h5>{listItem.propertyName}</h5>
                        <Info>
                          <h6>House No: {listItem.propertyAddress.number}</h6>
                          <h6>Street: {listItem.propertyAddress.street}</h6>
                          <h6>City: {listItem.propertyAddress.city}</h6>
                          <h6>Pincode: {listItem.propertyAddress.pincode}</h6>
                        </Info>
                        <p>Amenities: </p>{listItem.amenities.map((a, index) => <li key={index.toString()}>{a}</li>)}
                      </ListItems>

                    </Link>
                  </div>
                ))}
              </div>
            </ListRight>
          </div>
        </List>
      </div>
    )
  }
}
export default Listing;
