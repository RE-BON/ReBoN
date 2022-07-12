import React, { useState, useLayoutEffect } from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

export default function MainCard({ sort, mainInfo }) {
  const [like, setLike] = useState(false);

  useLayoutEffect(() => {
    console.log('main!!: ', mainInfo);
  }, []);

  const likeClick = ({ sort }) => {
    if (like) {
      setLike(false);
    } else {
      setLike(true);
    }
  };

  // const [mainInfo, setMainInfo] = useState([
  //   {
  //     id: 4,
  //     name: '인브리즈',
  //     star: 5.0,
  //     like: false,
  //     tags: [
  //       {
  //         id: 1,
  //         name: '포항',
  //       },
  //       {
  //         id: 3,
  //         name: '한동대',
  //       },
  //     ],
  //     image:
  //       'https://mblogthumb-phinf.pstatic.net/MjAxODA3MDNfMjAy/MDAxNTMwNjI5NjE1MTk1.cJeJTxszRfHGMw3q3hqSN4LLBS5scVV4lUBWsjEYLgcg.b1MlKG__mr8dVbZq7KGaFVZ3uttt9Ya_RBAJTgR8F9wg.JPEG.netusu/IMG_4822.jpg?type=w800',
  //   },
  //   {
  //     id: 5,
  //     name: '미스터 피자',
  //     star: 5.0,
  //     like: false,
  //     tags: [
  //       {
  //         id: 1,
  //         name: '포항',
  //       },
  //       {
  //         id: 4,
  //         name: '양덕',
  //       },
  //     ],
  //     image: 'https://cdn.tleaves.co.kr/news/photo/202202/1991_2819_1810.jpg',
  //   },
  //   {
  //     id: 6,
  //     name: '교촌 치킨',
  //     star: 3.0,
  //     like: false,
  //     tags: [
  //       {
  //         id: 1,
  //         name: '포항',
  //       },
  //       {
  //         id: 4,
  //         name: '양덕',
  //       },
  //     ],
  //     image: 'https://t1.daumcdn.net/cfile/tistory/99A07D405A8C32CC1C',
  //   },
  //   {
  //     id: 7,
  //     name: '환여 횟집',
  //     star: 4.0,
  //     like: false,
  //     tags: [
  //       {
  //         id: 1,
  //         name: '포항',
  //       },
  //       {
  //         id: 5,
  //         name: '환여동',
  //       },
  //     ],
  //     image: 'https://m.yorivery.com/data/goods/21/07/27//1000001108/1000001108_add3_070.jpg',
  //   },
  //   {
  //     id: 8,
  //     name: '온센',
  //     star: 5.0,
  //     like: true,
  //     tags: [
  //       {
  //         id: 1,
  //         name: '포항',
  //       },
  //       {
  //         id: 6,
  //         name: '영일대',
  //       },
  //     ],
  //     image: 'https://t1.daumcdn.net/cfile/tistory/992153345D78FD301F',
  //   },
  // ]);

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
        ? sort === 'star'
          ? mainInfo
              .sort((a, b) => b.star - a.star)
              .map((item, idx) => {
                var address = '/detail/' + item.id.toString();
                return (
                  <div className="mainCard">
                    {/* <img class="main-img" src={item.image} /> */}

                    {item.image ? (
                      <img class="main-img" src={item.image} />
                    ) : (
                      <img
                        class="main-img"
                        src="https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg"
                      />
                    )}

                    <div className="likeBtn-main">
                      {item.like ? (
                        <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick} />
                      ) : (
                        <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick} />
                      )}
                    </div>
                    <div className="mainCard-bottom">
                      <div className="titleRow">
                        <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                          <div className="placeName-main">{item.name}</div>
                        </Link>
                        <div className="starNum">{item.star}</div>
                      </div>
                      <div className="">
                        {item.tags.map((tag) => (
                          <span className="tag">{tag.name}</span>
                        ))}
                      </div>
                    </div>
                  </div>
                );
              })
          : sort === 'review'
          ? mainInfo
              .sort((a, b) => b.star - a.star)
              .map((item, idx) => {
                var address = '/detail/' + item.id.toString();
                return (
                  <div className="mainCard">
                    <img class="main-img" src={item.image} />

                    <div className="likeBtn-main">
                      {item.like ? (
                        <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick} />
                      ) : (
                        <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick} />
                      )}
                    </div>
                    <div className="mainCard-bottom">
                      <div className="titleRow">
                        <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                          <div className="placeName-main">{item.name}</div>
                        </Link>
                        <div className="starNum">{item.star}</div>
                      </div>
                      <div className="">
                        {item.tags.map((tag) => (
                          <span className="tag">{tag.name}</span>
                        ))}
                      </div>
                    </div>
                  </div>
                );
              })
          : sort === 'recent'
          ? mainInfo
              .sort((a, b) => b.id - a.id)
              .map((item, idx) => {
                var address = '/detail/' + item.id.toString();
                return (
                  <div className="mainCard">
                    <img class="main-img" src={item.image} />

                    <div className="likeBtn-main">
                      {item.like ? (
                        <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick} />
                      ) : (
                        <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick} />
                      )}
                    </div>
                    <div className="mainCard-bottom">
                      <div className="titleRow">
                        <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                          <div className="placeName-main">{item.name}</div>
                        </Link>
                        <div className="starNum">{item.star}</div>
                      </div>
                      <div className="">
                        {item.tags.map((tag) => (
                          <span className="tag">{tag.name}</span>
                        ))}
                      </div>
                    </div>
                  </div>
                );
              })
          : ''
        : ''}
      {}

      {/* {mainInfo.map((item,idx) => (
    
    ))} */}
    </>
  );
}
