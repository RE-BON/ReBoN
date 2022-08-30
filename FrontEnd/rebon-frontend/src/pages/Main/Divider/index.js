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
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const [like, setLike] = useState([]);

  useEffect(() => {
    setTimeout(function () {
      var isLike = [];
      if (data) {
        const result = data.filter((d) => d.id === checked);
        if (result.length > 0 && result[0].shop.length > 0) {
          setShopInfo(result[0].shop);

          result[0].shop.map((data, idx) => {
            if (data.like) {
              isLike[idx] = true;
            } else {
              isLike[idx] = false;
            }
            setLike(isLike);
          });
        } else setShopInfo(null);
      }
    }, 1200);
    setReady(true);
  }, [checked]);

  const toggleChange = (event) => {
    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);
  };

  const changeLike = (idx) => {
    var newLike = [...like];
    if (like[idx]) {
      newLike[idx] = false;

      setLike(newLike);
    } else {
      newLike[idx] = true;
      setLike(newLike);
    }
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
                defaultValue={'shopScore.star'}
              >
                <option value="shopScore.star">별점순</option>
                <option value="shopScore.reviewCount">리뷰많은 순</option>
                <option value="createdAt">최신순</option>
              </select>
            </div>
          </div>
          <div className="mainCard-wrapper">
            {toggleOn ? (
              <MainCard tagId={tagId} cateId={cateId} data={shopInfo} sort={sort} checked={checked} open="true" like={like} changeLike={changeLike} />
            ) : (
              <MainCard tagId={tagId} cateId={cateId} data={shopInfo} sort={sort} checked={checked} open="false" like={like} changeLike={changeLike} />
            )}
          </div>
        </>
      ) : (
        ''
      )}
    </>
  );
}
