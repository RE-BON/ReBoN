import React, { useState, useEffect } from 'react';
import axios from 'axios';
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
        console.log('MainCategoryData error');
      });
  }, []);

  return <>{restCategory && accoCategory && cafeCategory ? <MainShopData restCategory={restCategory} accoCategory={accoCategory} cafeCategory={cafeCategory} /> : ''}</>;
}
