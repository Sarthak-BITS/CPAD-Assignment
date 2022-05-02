import React, { Component } from 'react';
import HomeSectionA from './HomeSectionA';
import Navbar from "./Navbar";


class Home extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <HomeSectionA />
      </div>
    );
  }
}

export default Home;
