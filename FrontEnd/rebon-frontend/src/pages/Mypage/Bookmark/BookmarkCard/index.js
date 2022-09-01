import React, { useEffect, useState } from 'react';
import '../../../../styles/bookmark-card.css';

import { FaHeart } from 'react-icons/fa';
import { FiHeart } from 'react-icons/fi';
import axios from 'axios';

export default function BookmarkCard({ data }) {
  const [like, setLike] = useState([]);
  const token = window.sessionStorage.getItem('token');

  useEffect(() => {
    console.log('data!!!');
    console.log(data);
    if (data) {
      var isLike = [];

      for (var i = 0; i < data.length; i++) {
        isLike[i] = true;
      }
      setLike(isLike);
    }
  }, [data]);

  const likeClick = (shopId, idx, e) => {
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
          .post(url, { likeCount: 0, like: false }, config)
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
      {data
        ? data.map((item, idx) => {
            var address = '/detail/' + item.id.toString();
            var star = item.star.toFixed(1);
            console.log('식당이름');
            console.log(item.name);
            return (
              <div className="bookmarkCard">
                {item.image ? (
                  <img className="bookmarkCard-img" src={item.image} />
                ) : (
                  <img
                    className="bookmarkCard-img"
                    src="https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg"
                  />
                )}

                <div className="likeBtn-bookmark">
                  {like[idx] ? (
                    <FaHeart
                      className="heart-icon"
                      md={8}
                      size="22"
                      onClick={(e) => {
                        likeClick(item.id, idx, e);
                      }}
                    />
                  ) : (
                    <FiHeart
                      className="heart-icon-fi"
                      md={8}
                      size="22"
                      onClick={(e) => {
                        likeClick(item.id, idx, e);
                      }}
                    />
                  )}
                </div>

                <div className="bookmarkCard-bottom">
                  <div className="titleRow">
                    <div className="placeName">{item.name}</div>
                    <div className="starNum">{star}</div>
                  </div>
                  <ul className="tag-wrapper">
                    {item.tags.map((tag) => (
                      <li className="tag">{tag.name}</li>
                    ))}
                  </ul>
                </div>
              </div>
            );
          })
        : ''}
    </>
  );
}
