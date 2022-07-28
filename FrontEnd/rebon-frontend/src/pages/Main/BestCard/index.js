import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import { Link } from 'react-router-dom';

import { BsFillBookmarkFill } from 'react-icons/bs';

export default function BestCard({ data, checked }) {
  const [bestList, setBestList] = useState();
  useEffect(() => {
    setTimeout(function () {
      if (data) {
        const result = data.filter((d) => d.id === checked);
        if (result.length > 0 && result[0].shop.length > 0) setBestList(result[0].shop);
        else setBestList(null);
      }
    }, 100);
  }, [data, checked]);

  const [like, setLike] = useState(false);

  const likeClick = () => {
    if (like) {
      setLike(false);
    } else {
      setLike(true);
    }
  };

  const [bestInfo, setBestInfo] = useState([
    {
      id: 1,
      name: '팜스발리',
      star: 0.0,
      like: true,
      tags: [
        {
          id: 1,
          name: '포항',
        },
        {
          id: 3,
          name: '한동대',
        },
      ],
      image: 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Supreme_pizza.jpg/800px-Supreme_pizza.jpg',
    },
    {
      id: 3,
      name: '한동대 학관',
      star: 0.0,
      like: false,
      tags: [
        {
          id: 1,
          name: '포항',
        },
        {
          id: 3,
          name: '한동대',
        },
      ],
      image:
        'https://mblogthumb-phinf.pstatic.net/MjAxOTAxMTNfMTA0/MDAxNTQ3Mzc5NjQ1MzA2.jBPcIF5Tqd8GIrxEQhLG04tIQi33JGLThx4RQuimNVcg.2Nf25aUFMoCd4CdXBwv4HWg8ugDx0Ym9y9nhmEppNE0g.JPEG.let1997/Screenshot_20190113-204020_Naver_Blog.jpg?type=w800',
    },
  ]);

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
                      <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick} />
                    ) : (
                      <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick} />
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
