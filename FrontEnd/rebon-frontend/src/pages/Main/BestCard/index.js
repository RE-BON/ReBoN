import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import axios from 'axios';

import { BsFillBookmarkFill } from 'react-icons/bs';
import NoResult from "../../NoResult";

export default function BestCard({ data, checked }) {
  const [bestList, setBestList] = useState();
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const [like, setLike] = useState([]);

  useEffect(() => {
    setTimeout(function () {
      if (data) {
        const result = data.filter((d) => d.id === checked);
        var isLike = [];
        if (result.length > 0 && result[0].shop.length > 0) {
          setBestList(result[0].shop);
          result[0].shop.map((data, idx) => {
            if (data.like) {
              isLike[idx] = true;
            } else {
              isLike[idx] = false;
            }
          });
          setLike(isLike);
        } else setBestList(null);
      }
    }, 1200);
  }, [data, checked]);

  const likeClick = (shopId, isLike, idx, e) => {
    var newLike = [...like];
    if (like[idx]) {
      newLike[idx] = false;

      setLike(newLike);

      const config = {
        headers: { Authorization: `Bearer ${token}` },
      };
      if (token) {
        var url = 'http://3.34.139.61:8080/api/shops/' + shopId + '/unlike';
        axios
          .post(
            url,
            {
              likeCount: 0,
              like: false,
            },
            config
          )
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    } else {
      newLike[idx] = true;
      setLike(newLike);

      const config = {
        headers: { Authorization: `Bearer ${token}` },
      };
      if (token) {
        var url = 'http://3.34.139.61:8080/api/shops/' + shopId + '/like';
        axios
          .post(
            url,
            {
              likeCount: 1,
              like: true,
            },
            config
          )
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
              var star = item.star.toFixed(1);

              return (
                <div className="bestCard">
                  {item.image ? (
                    <img className="best-img" src={item.image} />
                  ) : (
                    <img
                      className="best-img"
                      src="https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg"
                    />
                  )}
                  <div className="titleRow-best">
                    <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                      <div className="placeName-best">{item.name}</div>
                    </Link>
                    <div className="starNum-best">{star}</div>
                  </div>

                  <div className="likeBtn">
                    {like[idx] ? (
                      <FaHeart
                        className="heart-icon"
                        md={8}
                        size="22"
                        onClick={(e) => {
                          likeClick(item.id, item.like, idx, e);
                        }}
                      />
                    ) : (
                      <FiHeart
                        className="heart-icon-fi"
                        md={8}
                        size="22"
                        onClick={(e) => {
                          likeClick(item.id, item.like, idx, e);
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
        : <NoResult/>}
    </>
  );
}
