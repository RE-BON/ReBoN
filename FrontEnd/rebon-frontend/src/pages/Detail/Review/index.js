import React, { useState, useRef, useEffect } from 'react';
import '../../../styles/review.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faShareNodes, faEllipsisVertical } from '@fortawesome/free-solid-svg-icons';
import { faHeart, faPenToSquare } from '@fortawesome/free-regular-svg-icons';
import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
import { MoreVertical, Trash, Edit } from 'react-feather';
import ReviewDropdown from './ReviewDropdown';
import ReviewModal from './ReviewModal';
import ReviewContent from './ReviewContent';
import { Link } from 'react-router-dom';
import { useMediaQuery } from 'react-responsive';

import '../../../styles/toggle.css';

import Toggle from 'react-toggle';
import axios from 'axios';
import { useLocation } from 'react-router';
import LogoutContent from '../../Logout/LogoutContent';

export default function Review({ shopName, shopImage, shopId }) {
  const { Kakao } = window;
  const location = useLocation();
  const [isMenuOpen, setIsMenuOpen] = useState(false); //모달 상태 관리 : 기본값 - 닫힘
  const dimmerRef = useRef(); // useRef를 활용하여 dim처리 해줘야 하는 부분
  const [toggleOn, setToggleOn] = useState(false);
  const [reviewInfo, setReviewInfo] = useState([]);
  const [reviewLike, setReviewLike] = useState();
  const [reviewTip, setReviewTip] = useState([]);
  const [ready, setReady] = useState(true);
  const [reviewReady, setReviewReady] = useState(false);
  const [sortReady, setSortReady] = useState(true);
  const [sort, setSort] = useState('1');
  const [logout, setLogout] = useState(false);
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });

  useEffect(() => {
    setReviewReady(false);
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    const shopNumber = Number(location.pathname.slice(8));

    const sortUrl = sort === '1' ? 'reviewScore.empathyCount,desc': sort === '2' ? 'sort=createdAt,desc' : sort === '3' ? 'reviewScore.star,desc' : 'reviewScore.star,asc'

    const url = 'http://3.34.139.61:8080/api/shops/' + shopNumber + '/reviews?sort='+ sortUrl
    axios
      // .get(`http://3.34.139.61:8080/api/shops/${shopNumber}/reviews`, config)
      .get(url, config)
      .then((response) => {
        setReviewInfo(response.data);
        var likeNum = 0;
        if (response.data) {
          for (var i = 0; i < response.data.length; i++) {
            if (response.data[i].liked) likeNum = likeNum + 1;
          }
          setReviewTip(
            response.data.filter(function (item) {
              return item.tip !== null;
            })
          );
        }

        setReviewLike(likeNum);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setLogout(true);
        } else console.log('Review error');
      });

    setReviewReady(true);
  }, [shopImage,sort]);


  const shareKakao = () => {
    Kakao.Link.sendDefault({
      objectType: 'feed',
      content: {
        title: 'ReBon: ',
        description: "'" + shopName + "'" + ' 공유',
        imageUrl: shopImage[0] ? shopImage[0].url : shopImage,
        link: {
          mobileWebUrl: '모바일 url!',
          androidExecParams: 'test',
        },
      },
      buttons: [
        {
          title: '웹으로 이동',
          link: {
            mobileWebUrl: 'http://rebon.s3-website.ap-northeast-2.amazonaws.com/detail/' + shopId,
            webUrl: 'http://rebon.s3-website.ap-northeast-2.amazonaws.com/detail/' + shopId,
          },
        },
        {
          title: '앱으로 이동',
          link: {
            mobileWebUrl: 'http://rebon.s3-website.ap-northeast-2.amazonaws.com/detail/' + shopId,
            webUrl: 'http://rebon.s3-website.ap-northeast-2.amazonaws.com/detail/' + shopId,
          },
        },
      ],
    });
  };

  const openMenu = () => {
    setIsMenuOpen(true);
  };

  const closMenu = () => {
    if (isMenuOpen === true) return setIsMenuOpen(false);
  };

  const toggleChange = (event) => {
    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);
  };

  return (
    <>
      <div className="review-wrapper">
        {isMobile ? (
          <>
            <div className="review-title-wrapper">
              <h4 className="review-name">리뷰</h4> <div className="review-num">({reviewInfo.length})</div>
            </div>
          </>
        ) : (
          <div className="review-title-wrapper">
            <h4 className="review-name">리뷰</h4>
            <div className="review-num">({reviewInfo.length})</div>
            <div className="review-write-wrapper">
              <FontAwesomeIcon icon={faPenToSquare} className="review-write-icon" size="1x" />
              <Link to={`../post/${shopId}`} style={{ textDecoration: 'none' }}>
                <span className="review-write-button">리뷰쓰기</span>
              </Link>
            </div>
            <div className="review-like-wrapper">
              <FontAwesomeIcon icon={faHeart} className="review-icon" size="1x" />
              <span className="review-like-num">{reviewLike}</span>
            </div>
            <button className="review-share-wrapper" onClick={shareKakao}>
              <FontAwesomeIcon icon={faShareNodes} className="review-icon" size="1x" />
              <span className="review-share-name">공유</span>
            </button>
          </div>
        )}
        <hr className="bold-hr" />
        <div className="review-top-wrapper">
          <div className="review-toggle-wrapper">
            <Toggle className="review-toggle" id="cheese-status" defaultChecked={toggleOn} onChange={toggleChange} />
            <div className="review-toggle-label">나만의 꿀팁</div>
          </div>
          <div className="review-order">
            <select
              // className="form-select"
              aria-label="Default select example"
              className="review-select form-select"
              // onChange={sortChange}
              onChange={(e) => {
                setSort(e.target.value);
              }}
              defaultValue={'1'}
              style={{ paddingBottom: '5px' }}
            >
              <option value="1" className="review-select-option" style={{ fontSize: '7px' }}>
                좋아요순
              </option>
              <option value="2">최신순</option>
              <option value="3">평점높은순</option>
              <option value="4">평점낮은순</option>
            </select>
          </div>
        </div>
        {logout ? (
          <LogoutContent />
        ) : reviewReady && sortReady ? (
          !toggleOn ? (
            reviewInfo.length > 0 && sortReady ? (
              <ReviewContent data={reviewInfo} sort={sort} toggleOn={toggleOn} />
            ) : (
              ''
            )
          ) : reviewTip.length > 0 && sortReady ? (
            <ReviewContent data={reviewTip} sort={sort} toggleOn={toggleOn} />
          ) : (
            ''
          )
        ) : (
          ''
        )}

        {isMobile ? (
          <div className="review-footer-sticky">
            <button className="review-share-wrapper" onClick={shareKakao}>
              <FontAwesomeIcon icon={faShareNodes} className="review-icon" size="1x" />
            </button>
            {/* <div className="review-footer-line"></div> */}
            <button className="review-like-wrapper">
              <FontAwesomeIcon icon={faHeart} className="review-icon" size="1x" />
            </button>
            <div className="review-write-wrapper">
              <FontAwesomeIcon icon={faPenToSquare} className="review-write-icon" size="1x" />
              <Link to={`/post/${shopId}`} style={{ textDecoration: 'none' }}>

                <span className="review-write-button">리뷰쓰기</span>
              </Link>
            </div>
          </div>
        ) : (
          ''
        )}
      </div>
    </>
  );
}
