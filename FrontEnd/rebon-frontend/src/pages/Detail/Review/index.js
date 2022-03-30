import React, { useState, useRef } from 'react';
import '../../../styles/review.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faShareNodes, faEllipsisVertical } from '@fortawesome/free-solid-svg-icons';
import { faHeart, faPenToSquare } from '@fortawesome/free-regular-svg-icons';
import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
import { MoreVertical, Trash, Edit } from 'react-feather';

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

export default function Review() {
  const [isMenuOpen, setIsMenuOpen] = useState(false); //모달 상태 관리 : 기본값 - 닫힘
  const dimmerRef = useRef(); // useRef를 활용하여 dim처리 해줘야 하는 부분

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
    // 모달을 열겠다.
  };

  const closMenu = () => {
    if (isMenuOpen === true) return setIsMenuOpen(false);
    //모달이 열렸을 때만 닫아주겠다.
  };

  return (
    <div className="review-wrapper">
      <div className="review-title-wrapper">
        <h4 className="review-name">리뷰</h4>
        <div className="review-num">(12)</div>
        <div className="review-write-wrapper">
          <FontAwesomeIcon icon={faPenToSquare} className="review-icon" size="1x" />
          <span className="review-write-button">리뷰쓰기</span>
        </div>
        <div className="review-like-wrapper">
          <FontAwesomeIcon icon={faHeart} className="review-icon" size="1x" />
          <span className="review-like-num">2,569</span>
        </div>
        <div className="review-share-wrapper">
          <FontAwesomeIcon icon={faShareNodes} className="review-icon" size="1x" />
          <span className="review-share-name">공유</span>
        </div>
      </div>
      <hr className="bold-hr" />
      <div className="review-order">
        <select class="form-select" aria-label="Default select example" className="review-select">
          <option selected value="1" className="review-select-option">
            베스트 리뷰순
          </option>
          <option value="2">평점순</option>
          <option value="3">조회순</option>
        </select>
      </div>
      <div className="review-content">
        <div className="review-user-image">
          <FontAwesomeIcon icon={faCircleUser} size="5x" color="#BDBDBD" />
        </div>
        <div className="review-user">
          <div className="review-list-icon">
            <FontAwesomeIcon icon={faEllipsisVertical} size="1x" className="review-list-icon" color="#BDBDBD" onClick={() => ActionMenu()} />
          </div>
          <div className="review-user-name">홍길동</div>
          <div className="review-user-time">10분전</div>
          <div className="review-tip-wrapper">
            <span className="review-tip-name">나만의 꿀팁</span>
            <span className="review-tip-content">2층 창가자리 추천이요!</span>
          </div>
          <div className="review-post">
            중구 근처 레스토랑 찾다가 와봤는데 분위기도 너무 좋고 맛도 너무 좋네요 !! 아이들이랑 왔는데 식기도 따로 주시고 직원분들 서비스, 맛 하나 빠지는게 없이 식사 만족스럽게 잘
            했습니다. 양식 드시고 싶다면 한번씩 와보시는거 왕 추천 ~~!!
          </div>
          <div className="review-like-button">
            좋아요
            <FontAwesomeIcon icon={faHeart} className="review-icon" size="sm" color="#5F686F" />
          </div>
        </div>
      </div>
      <div className="review-content">
        <div className="review-user-image">
          <FontAwesomeIcon icon={faCircleUser} size="5x" color="#BDBDBD" />
        </div>
        <div className="review-user">
          <div className="review-list-icon">
            <FontAwesomeIcon icon={faEllipsisVertical} size="1x" className="review-list-icon" color="#BDBDBD" onClick={ActionMenu} />
          </div>
          <div className="review-user-name">홍길동</div>
          <div className="review-user-time">10분전</div>
          <div className="review-tip-wrapper">
            <span className="review-tip-name">나만의 꿀팁</span>
            <span className="review-tip-content">2층 창가자리 추천이요!</span>
          </div>
          <div className="review-post">
            중구 근처 레스토랑 찾다가 와봤는데 분위기도 너무 좋고 맛도 너무 좋네요 !! 아이들이랑 왔는데 식기도 따로 주시고 직원분들 서비스, 맛 하나 빠지는게 없이 식사 만족스럽게 잘
            했습니다. 양식 드시고 싶다면 한번씩 와보시는거 왕 추천 ~~!!
          </div>
          <div className="review-like-button">
            좋아요
            <FontAwesomeIcon icon={faHeart} className="review-icon" size="sm" color="#5F686F" />
          </div>
          <div className="review-image-wrapper">
            <img className="review-image" alt="review-image" src="image/detail.png" />
            <img className="review-image" alt="review-image" src="image/detail.png" />
          </div>
        </div>
      </div>
    </div>
  );
}
