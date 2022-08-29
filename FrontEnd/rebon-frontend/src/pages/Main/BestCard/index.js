import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import axios from 'axios';

import { BsFillBookmarkFill } from 'react-icons/bs';

export default function BestCard({ data, checked }) {
  const [bestList, setBestList] = useState();
  useEffect(() => {
    setTimeout(function () {
      if (data) {
        const result = data.filter((d) => d.id === checked);
        console.log(result);
        if (result.length > 0 && result[0].shop.length > 0) setBestList(result[0].shop);
        else setBestList(null);
      }
    }, 100);
  }, [data, checked]);

  const [like, setLike] = useState(false);

  const [token, setToken] = useState(window.sessionStorage.getItem('token'));

  const likeClick = (shopId, e) => {
    if (like) {
      setLike(false);
      console.log('토큰');
      console.log(token);
    } else {
      setLike(true);
      const config = {
        headers: { Authorization: `Bearer ${token}` },
      };
      if (token) {
        var url = 'http://3.34.139.61:8080/api/shops/' + shopId + '/like';
        axios
          .post(url, config)
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  };

  return (
    <>
      {bestList
        ? bestList.map((item, idx) => {
            if (idx < 4) {
              var address = '/detail/' + item.id.toString();

              return (
                <div className="bestCard">
                  {item.image ? (
                    <img class="best-img" src={item.image} />
                  ) : (
                    <img
                      class="best-img"
                      src="https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg"
                    />
                  )}
                  <div className="titleRow-best">
                    <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                      <div className="placeName-best">{item.name}</div>
                    </Link>
                    <div className="starNum-best">{item.star}</div>
                  </div>

                  <div className="likeBtn">
                    {item.like ? (
                      <FaHeart
                        className="heart-icon"
                        md={8}
                        size="22"
                        onClick={(e) => {
                          likeClick(item.id, e);
                        }}
                      />
                    ) : (
                      <FiHeart
                        className="heart-icon-fi"
                        md={8}
                        size="22"
                        onClick={(e) => {
                          likeClick(item.id, e);
                        }}
                      />
                    )}
                  </div>

                  <BsFillBookmarkFill className="bookmark-icon" md={8} size="32" />
                  <div className="best-num">{idx + 1}</div>

                  <div className="tagRow-best">
                    {item.tags.map((tag) => (
                      <span className="tag-best">{tag.name}</span>
                    ))}
                  </div>
                </div>
              );
            }
          })
        : ''}
    </>
  );
}
