import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';

export default function MainCard({ tagId, cateId, data, checked, open, sort }) {
  const [like, setLike] = useState(false);
  const [mainInfo, setMainInfo] = useState(null);
  const [subId, setSubId] = useState();

  useEffect(() => {
    setTimeout(function () {
      if (data) {
        var url = 'http://3.34.139.61:8080/api/shops?tag=' + tagId + '&category=' + cateId + '&subCategories=' + checked + '&open=' + open + '&sort=' + sort + '%2Cdesc';
        axios.get(url).then((response) => {
          setMainInfo(response.data);
        });
      }
    }, 100);
  }, [data, checked, open, sort]);

  const likeClick = ({ sort }) => {
    if (like) {
      setLike(false);
    } else {
      setLike(true);
    }
  };

  const StyledLink = styled(Link)`
    box-sizing: border-box;
    display: block;
    text-align: center;
    text-decoration: none;
    color: black;
    hover: none;
  `;

  return (
    <>
      {mainInfo
        ? mainInfo.map((item, idx) => {
            var address = '/detail/' + item.id.toString();
            return (
              <div className="mainCard">
                {item.image ? (
                  <img class="main-img" src={item.image} />
                ) : (
                  <img
                    class="main-img"
                    src="https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg"
                  />
                )}

                <div className="likeBtn-main">
                  {item.like ? <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick} /> : <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick} />}
                </div>
                <div className="mainCard-bottom">
                  <div className="titleRow">
                    <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                      <div className="placeName-main">{item.name}</div>
                    </Link>
                    <div className="starNum">{item.star}</div>
                  </div>
                  {/* <div className="">
                        {item.tags.map((tag) => (
                          <span className="tag">{tag.name}</span>
                        ))}
                      </div> */}
                </div>
              </div>
            );
          })
        : ''}
      {}
    </>
  );
}
