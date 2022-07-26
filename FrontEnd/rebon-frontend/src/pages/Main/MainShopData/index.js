import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router';
import Main from '../../Main';

export default function MainShopData({ restCategory, accoCategory, cafeCategory }) {
  const location = useLocation();
  const [ready, setReady] = useState(false);
  const [restData, setRestData] = useState(null);
  const [accoData, setAccoData] = useState(null);
  const [cafeData, setCafeData] = useState(null);

  useEffect(() => {
    setReady(false);

    var restArr = [];
    var accoArr = [];
    var cafeArr = [];

    restCategory.map((data, index) => {
      var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=1&subCategories=' + data.id + '&open=false&sort=shopScore.star%2Cdesc';
      axios.get(url).then((response) => {
        const restState = { id: data.id, shop: response.data };
        restArr.push(restState);
      });
    });
    setRestData(restArr);

    accoCategory.map((data, index) => {
      var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=1&subCategories=' + data.id + '&open=false&sort=shopScore.star%2Cdesc';
      axios.get(url).then((response) => {
        const accoState = { id: data.id, shop: response.data };
        accoArr.push(accoState);
      });
    });
    setAccoData(accoArr);

    cafeCategory.map((data, index) => {
      var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=1&subCategories=' + data.id + '&open=false&sort=shopScore.star%2Cdesc';
      axios.get(url).then((response) => {
        const cafeState = { id: data.id, shop: response.data };
        cafeArr.push(cafeState);
      });
    });
    setCafeData(cafeArr);

    setReady(true);
  }, []);

  return (
    <>{ready ? <Main restCategory={restCategory} accoCategory={accoCategory} cafeCategory={cafeCategory} restData={restData} accoData={accoData} cafeData={cafeData} /> : ''}</>
  );
}
