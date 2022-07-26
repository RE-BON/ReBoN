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

// import 'react-toggle/style.css';
import '../../../styles/toggle.css';

import Toggle from 'react-toggle';

// const CustomToggle = React.forwardRef(({ children, onClick }, ref) => (
//   <Link
//     to=""
//     ref={ref}
//     onClick={(e) => {
//       e.preventDefault();
//       onClick(e);
//     }}
//   >
//     {children}
//   </Link>
// ));

export default function Review({ shopName, shopImage, shopId }) {
  const { Kakao } = window;
  const [isMenuOpen, setIsMenuOpen] = useState(false); //모달 상태 관리 : 기본값 - 닫힘
  const dimmerRef = useRef(); // useRef를 활용하여 dim처리 해줘야 하는 부분
  const [toggleOn, setToggleOn] = useState(false);
  const [reviewLike, setReviewLike] = useState();
  const [reviewTip, setReviewTip] = useState([]);
  const [ready, setReady] = useState(true);
  const [reviewReady, setReviewReady] = useState(false);
  const [sortReady, setSortReady] = useState(true);
  const [sort, setSort] = useState(1);
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });
  const [review, setReview] = useState([
    {
      id: 1,
      authorName: 'test',
      shopName: '팜스발리',
      content: '맛이 좋아요',
      tip: '필수로 시키자',
      star: 5,
      empathyCount: 0,
      images: [],
      liked: true,
    },
    {
      id: 2,
      authorName: 'ralph',
      shopName: '팜스발리',
      content: '맛이 별로예요',
      tip: '김치 불고기 피자가 제일 맛있어요',
      star: 2,
      empathyCount: 6,
      images: [],
      liked: false,
    },
    {
      id: 3,
      authorName: 'peace',
      shopName: '팜스발리',
      content:
        '피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요',
      tip: null,
      star: 4,
      empathyCount: 20,
      images: [],
      liked: true,
    },
  ]);
  const [reviewInfo, setReviewInfo] = useState([
    {
      id: 1,
      authorName: 'test',
      shopName: '팜스발리',
      content: '맛이 좋아요',
      tip: '필수로 시키자',
      star: 5,
      empathyCount: 0,
      images: [],
      liked: true,
    },
    {
      id: 2,
      authorName: 'ralph',
      shopName: '팜스발리',
      content: '맛이 별로예요',
      tip: '김치 불고기 피자가 제일 맛있어요',
      star: 2,
      empathyCount: 6,
      images: [],
      liked: false,
    },
    {
      id: 3,
      authorName: 'peace',
      shopName: '팜스발리',
      content:
        '피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요',
      tip: null,
      star: 4,
      empathyCount: 20,
      images: [],
      liked: true,
    },
  ]);

  useEffect(() => {
    setReviewReady(false);
    var likeNum = 0;
    var tips = [];
    var hi = [];
    for (var i = 0; i < reviewInfo.length; i++) {
      if (reviewInfo[i].liked) likeNum = likeNum + 1;
      if (reviewInfo[i].tip !== null) tips.push(reviewInfo[i]);
      setReviewTip(tips);
    }

    setReviewLike(likeNum);
    setReviewReady(true);
  }, []);

  const shareKakao = () => {
    Kakao.Link.sendDefault({
      objectType: 'feed',
      content: {
        title: 'ReBon: ',
        description: "'" + shopName + "'" + ' 공유',
        imageUrl: shopImage,
        link: {
          mobileWebUrl: '모바일 url!',
          androidExecParams: 'test',
        },
      },
      buttons: [
        {
          title: '웹으로 이동',
          link: {
            mobileWebUrl: 'http://localhost:3000/detail/' + shopId,
            webUrl: 'http://localhost:3000/detail/' + shopId,
          },
        },
        {
          title: '앱으로 이동',
          link: {
            mobileWebUrl: 'http://localhost:3000/detail/' + shopId,
            webUrl: 'http://localhost:3000/detail/' + shopId,
          },
        },
      ],
    });
  };

  // useEffect(() => {
  //   axios
  //     .get('http://34.238.48.93:8080/api/shops/1')
  //     .then((response) => {
  //       setShopInfo(response.data);
  //       console.log(shopInfo);
  //       console.log(shopInfo.tags);
  //     })
  //     .catch((error) => {
  //       console.log('error');
  //     });
  // }, []);

  const ActionMenu = () => {
    console.log('hello');
    // return (
    <Dropdown>
      <Dropdown.Toggle>
        <MoreVertical size="15px" className="text-secondary" />
      </Dropdown.Toggle>
      <Dropdown.Menu align="end">
        <Dropdown.Header>SETTINGS</Dropdown.Header>
        <Dropdown.Item eventKey="1">
          {' '}
          <Edit size="18px" className="dropdown-item-icon" /> 활동내역 보기
        </Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>;
    // );
  };

  const openMenu = () => {
    setIsMenuOpen(true);
  };

  const closMenu = () => {
    if (isMenuOpen === true) return setIsMenuOpen(false);
  };

  const toggleChange = (event) => {
    setReady(false);

    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);

    setReady(true);
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
              <Link to="../post" style={{ textDecoration: 'none' }}>
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
              class="form-select"
              aria-label="Default select example"
              className="review-select"
              // onChange={sortChange}
              onChange={(e) => {
                setSort(e.target.value);
              }}
            >
              <option selected value="1" className="review-select-option">
                좋아요순
              </option>
              <option value="2">최신순</option>
              <option value="3">평점높은순</option>
              <option value="4">평점낮은순</option>
            </select>
          </div>
        </div>

        {ready && reviewReady && sortReady ? (
          !toggleOn ? (
            reviewInfo.length > 0 && sortReady ? (
              <ReviewContent data={reviewInfo} sort={sort} />
            ) : (
              ''
            )
          ) : reviewTip.length > 0 && sortReady ? (
            <ReviewContent data={reviewTip} sort={sort} />
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
              <Link to="../post" style={{ textDecoration: 'none' }}>
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
