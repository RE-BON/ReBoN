import React, { useEffect, useState } from 'react';
import '../../../../styles/bookmark-card.css';

import { FaHeart } from 'react-icons/fa';

export default function BookmarkCard({ data }) {
  const [like, setLike] = useState([]);

  useEffect(() => {
    console.log('data!!!');
    console.log(data);
  });

  return (
    <>
      {data
        ? data.map((item, idx) => {
            var address = '/detail/' + item.id.toString();
            var star = item.star.toFixed(1);
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
                  <FaHeart md={8} size="22" />
                </div>

                <div className="bookmarkCard-bottom">
                  <div className="titleRow">
                    <div className="placeName">{item.name}</div>
                    <div className="starNum">{item.star}</div>
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
