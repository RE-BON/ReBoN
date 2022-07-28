import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import Toggle from 'react-toggle';
import MainCard from '../MainCard';
import axios from 'axios';

export default function Divider({ data, tagId, cateId, checked }) {
  const [toggleOn, setToggleOn] = useState(false);
  const [sort, setSort] = useState('shopScore.star');
  const [shopInfo, setShopInfo] = useState(null);
  const [ready, setReady] = useState(false);

  useEffect(() => {
    setTimeout(function () {
      if (data) {
        const result = data.filter((d) => d.id === checked);
        if (result.length > 0 && result[0].shop.length > 0) setShopInfo(result[0].shop);
        else setShopInfo(null);
      }
    }, 100);
    setReady(true);
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
                <option value="shopScore.star" selected>
                  별점순
                </option>
                <option value="shopScore.reviewCount">리뷰많은 순</option>
                <option value="createdAt">최신순</option>
              </select>
            </div>
          </div>
          <div className="mainCard-wrapper">
            {toggleOn ? (
              <MainCard tagId={tagId} cateId={cateId} data={shopInfo} sort={sort} checked={checked} open="true" />
            ) : (
              <MainCard tagId={tagId} cateId={cateId} data={shopInfo} sort={sort} checked={checked} open="false" />
            )}
          </div>
        </>
      ) : (
        ''
      )}
    </>
  );
}
