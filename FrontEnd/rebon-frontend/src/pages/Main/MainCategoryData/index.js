import React, { useState, useEffect, useLayoutEffect } from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

import axios from 'axios';

import { useLocation } from 'react-router';
import MainShopData from '../MainShopData';

export default function MainCategoryData() {
  const [restCategory, setRestCategory] = useState();
  const [accoCategory, setAccoCategory] = useState();
  const [cafeCategory, setCafeCategory] = useState();

  useEffect(() => {
    axios
    .get('http://3.34.139.61:8080/api/categories')
    .then((response) => {
      setRestCategory(response.data[0].children);
      setAccoCategory(response.data[1].children);
      setCafeCategory(response.data[2].children);
    })
    .catch((error) => {
      console.log('MainData/Category error');
    });
  }, []);


  return (
    <>
    {restCategory&&accoCategory&& cafeCategory? (<MainShopData restCategory={restCategory} accoCategory={accoCategory} cafeCategory={cafeCategory}/>):('')}
    </>
    );
}
