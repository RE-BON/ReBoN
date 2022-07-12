import React, { useState, useEffect, useLayoutEffect } from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

import axios from 'axios';

import { useLocation } from 'react-router';
import Main from '../../Main';

export default function MainData() {
  const [restCategory, setRestCategory] = useState();
  const [accoCategory, setAccoCategory] = useState();
  const [cafeCategory, setCafeCategory] = useState();

  const [restData, setRestData] = useState();
  const [accoData, setAccoData] = useState();
  const [cafeData, setCafeData] = useState();

  const [restChecked, setRestChecked] = useState(0);
  const [cafeChecked, setCafeChecked] = useState(0);
  const [accoChecked, setAccoChecked] = useState(0);

  const [ready, setReady] = useState(false);

  const location = useLocation();

  useLayoutEffect(() => {
    axios
      .get('http://3.34.139.61:8080/api/categories')
      .then((response) => {
        setReady(false);
        setRestCategory(response.data[0].children);
        setAccoCategory(response.data[1].children);
        setCafeCategory(response.data[2].children);

        var restShop = [];
        var accoShop = [];
        var cafeShop = [];

        response.data[0].children.map((rest, index) => {
          var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=1&subCategories=' + rest.id + '&open=false';
          axios.get(url).then((response) => {
            restShop.push(response.data);
          });
        });

        response.data[1].children.map((acco, index) => {
          var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=2&subCategories=' + acco.id + '&open=false';
          axios.get(url).then((response) => {
            accoShop.push(response.data);
          });
        });

        response.data[2].children.map((cafe, index) => {
          var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=3&subCategories=' + cafe.id + '&open=false';
          axios.get(url).then((response) => {
            cafeShop.push(response.data);
          });
        });

        setRestData(restShop);
        setAccoData(accoShop);
        setCafeData(cafeShop);

        setReady(true);
      })
      .catch((error) => {
        console.log('MainData error');
      });
  }, []);

  return (
    <>{ready ? <Main restCategory={restCategory} accoCategory={accoCategory} cafeCategory={cafeCategory} restData={restData} accoData={accoData} cafeData={cafeData} /> : ''}</>
    // <>
    //   <Main restCategory={restCategory} accoCategory={accoCategory} cafeCategory={cafeCategory} restData={restData} accoData={accoData} cafeData={cafeData} />{' '}
    // </>
  );
}
