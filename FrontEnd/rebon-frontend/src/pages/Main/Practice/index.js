import React, { useState, useEffect, useLayoutEffect } from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

import axios from 'axios';
import { useLocation } from 'react-router';

export default function Practice({data}) {
    useEffect(() => {
        console.log("============Practice============")
        console.log("Practice 받아온 값. data: ",data);

       

}, []);


  return (
    <>
    {/* {restShop? (    <Practice data={restShop}/>):('')} */}
    {/* {restCategory ? (<MainShopData rest={restCategory} acco={accoCategory} cafe={cafeCategory}/>):('')} */}
    </>
    );
}
