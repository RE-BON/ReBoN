import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import Toggle from 'react-toggle';
import MainCard from '../MainCard';
import axios from 'axios';

export default function Divider({ data, checked, tagId }) {
  const [toggleOn, setToggleOn] = useState(false);
  const [sort, setSort] = useState('star');
  const [shopInfo, setShopInfo] = useState(null);
  const [openShopInfo, setOpenShopInfo] = useState(null);
  const [ready, setReady] = useState(false);
  const [subId, setSubId] = useState();

  useEffect(() => {
    const result = data.filter((d) => d.id === checked);
    if (data) {
      const result = data.filter((d) => d.id === checked);
      setSubId(result[0].id);

      //closed Shop Info
      if (result.length > 0 && result[0].shop.length > 0) setShopInfo(result[0].shop);
      else setShopInfo(null);

      //open Shop Info
      var url = 'http://3.34.139.61:8080/api/shops?tag=' + tagId + '&category=1&subCategories=' + result[0].id + '&open=true';
      axios
        .get(url)
        .then((response) => {
          setOpenShopInfo(response.data);
          setReady(true);
        })
        .catch((error) => {
          console.log('Divider error');
        });
    }
  }, [checked]);

  const toggleChange = (event) => {
    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);
  };

  return (
    <>
      {ready ? (
        <>
          <div className="divider-main">
            <div className="divider-right">
              <Toggle className="main-toggle" id="" defaultChecked={toggleOn} onChange={toggleChange} />
              <span className="main-toggle-label">현재 영업중</span>
              <select
                className="filter"
                name="filter"
                onChange={(e) => {
                  setSort(e.target.value);
                }}
              >
                <option value="star" selected>
                  별점순
                </option>
                <option value="review">리뷰많은 순</option>
                <option value="recent">최신순</option>
              </select>
            </div>
          </div>
          <div className="mainCard-wrapper">
            {toggleOn ? <MainCard sort="star" data={openShopInfo} checked={checked} open="true" /> : <MainCard sort="star" data={shopInfo} checked={checked} open="false" />}
          </div>
        </>
      ) : (
        ''
      )}
    </>
  );
}
