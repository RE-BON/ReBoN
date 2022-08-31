import React, { useEffect, useState, useLayoutEffect } from 'react';
import '../../../../styles/bookmark-card.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';

export default function BookmarkCard({ data }) {
  const [bookmarkList, setBookmarkList] = useState();
  const [ready, setReady] = useState(false);
  const [like, setLike] = useState([]);

  useEffect(() => {
    setTimeout(function () {
      console.log('data!!');
      console.log(data);
      setBookmarkList(data);
    }, 1200);
  }, [data]);

  return (
    <>
      {data
        ? data.map((item, idx) => {
            var address = '/detail/' + item.id.toString();
            var star = item.star.toFixed(1);
            console.log('item');
            console.log(item.id);
            console.log(idx);
            return (
              <div className="bookmarkCard">
                <div className="bookmarkCard-img">
                  <FiHeart md={8} size="22" />
                </div>
                <div className="bookmarkCard-bottom">
                  <div className="titleRow">
                    <div className="placeName">{item.name}</div>
                    <div className="starNum">4.9</div>
                  </div>
                  <ul className="tag-wrapper">
                    <li className="tag">칠포해수욕장</li>
                    <li className="tag">칠포읍</li>
                    <li className="tag">한식</li>
                  </ul>
                </div>
              </div>
            );
          })
        : ''}
    </>
  );
}
