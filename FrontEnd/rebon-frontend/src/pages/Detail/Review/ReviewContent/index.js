import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
// import SearchBar from './SearchBar';
import { Link } from 'react-router-dom';

import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
import { MoreVertical, Trash, Edit, AlertOctagon, X } from 'react-feather';
import { faCircleUser, faShareNodes, faEllipsisVertical } from '@fortawesome/free-solid-svg-icons';
import { faHeart as regularHeart, faPenToSquare } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';

import ReviewDropdown from '../ReviewDropdown';
import ReviewModal from '../ReviewModal';
import ReviewStar from '../ReviewStar';
import '../../../../styles/review.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from "axios";
// import { solid, regular, brands } from '@fortawesome/fontawesome-svg-core/import.macro'; // <-- import styles to be used

export default function ReviewContent({ data, sort, toggleOn }) {
  const token = useState(window.sessionStorage.getItem('token'));
  const [alllikeData, setAllLikeData] = useState([]);
  // var ID = parseInt(window.sessionStorage.getItem('id'));

  useEffect(() => {
    // if(location.state.email !=null){
    //   console.log(location.state.email);
    // }

  },[toggleOn]);
  //
  // useEffect(() => {
  //   setReviewReady(false);
  //   const config = {
  //     headers: { Authorization: `Bearer ${token}` },
  //   };
  //   const shopNumber = Number(location.pathname.slice(8));
  //   axios
  //     .get(`http://3.34.139.61:8080/api/shops/${shopNumber}/reviews`, config)
  //     .then((response) => {
  //       console.log("Review: ", response.data);
  //       setReviewInfo(response.data);
  //     })
  //     .catch((error) => {
  //       console.log('Review error');
  //     });
  // },[]);

  const onToggle = (reviewId) => {
    if (alllikeData.includes(reviewId)) {
      deleteLike(reviewId);
    } else {
      addLike(reviewId);
    }
  };

  const deleteLike = async (reviewId) => {
    setAllLikeData(alllikeData.filter((item) => item !== reviewId));
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    const response = await axios.post(`http://3.34.139.61:8080/api/reviews/${reviewId}/unempathize`, config);
    console.log("delete, response : ",response.data);
  };

  const addLike = async (reviewId) => {
    setAllLikeData((prevState) => [...prevState, reviewId]);
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    const response = await axios.post(`http://3.34.139.61:8080/api/reviews/${reviewId}/empathize`, config);
  };

  return (
    <>
      {sort === '1'
        ? data
            .sort((a, b) => b.empathyCount - a.empathyCount)
            .map((info, index) => (
              <div className="review-content" key={index}>
                {' '}
                <div className="review-user-image">
                  <FontAwesomeIcon icon={faCircleUser} className="review-user-icon" color="#BDBDBD" />
                </div>
                <div className="review-user">
                  <div className="review-list-icon">
                    <ReviewModal />
                  </div>
                  <div className="review-user-name-star">
                    <div className="review-user-name">{info.authorName}</div>
                    <ReviewStar star={info.star} />
                  </div>

                  {info.tip ? (
                    <div className="review-tip-wrapper">
                      <span className="review-tip-name">나만의 꿀팁</span>
                      <span className="review-tip-content">{info.tip}</span>
                    </div>
                  ) : (
                    ''
                  )}
                  <div className="review-post">{info.content}</div>
                  <div className="review-like-button">
                    <button className="review-button">
                      {/*{alllikeData.includes(info.id) ? (*/}
                      {/*  <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />*/}
                      {/*) : (*/}
                      {/*  <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />*/}
                      {/*)}*/}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))
        : sort === '2'
        ? data
            .sort((a, b) => b.id - a.id)
            .map((info,index) => (
              <div className="review-content" key={index}>
                {' '}
                <div className="review-user-image">
                  <FontAwesomeIcon icon={faCircleUser} className="review-user-icon" color="#BDBDBD" />
                </div>
                <div className="review-user">
                  <div className="review-list-icon">
                    <ReviewModal />
                  </div>
                  <div className="review-user-name-star">
                    <div className="review-user-name">{info.authorName}</div>
                    <ReviewStar star={info.star} />
                  </div>

                  {info.tip ? (
                    <div className="review-tip-wrapper">
                      <span className="review-tip-name">나만의 꿀팁</span>
                      <span className="review-tip-content">{info.tip}</span>
                    </div>
                  ) : (
                    ''
                  )}

                  <div className="review-post">{info.content}</div>
                  <div className="review-like-button">
                    <button className="review-button">
                      {/*{alllikeData.includes(info.id) ? (*/}
                      {/*  <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />*/}
                      {/*) : (*/}
                      {/*  <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />*/}
                      {/*)}*/}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))
        : sort === '3'
        ? data
            .sort((a, b) => b.star - a.star)
            .map((info, index) => (
              <div className="review-content" key={index}>
                {' '}
                <div className="review-user-image">
                  <FontAwesomeIcon icon={faCircleUser} className="review-user-icon" color="#BDBDBD" />
                </div>
                <div className="review-user">
                  <div className="review-list-icon">
                    <ReviewModal />
                  </div>
                  <div className="review-user-name-star">
                    <div className="review-user-name">{info.authorName}</div>
                    <ReviewStar star={info.star} />
                  </div>

                  {info.tip ? (
                    <div className="review-tip-wrapper">
                      <span className="review-tip-name">나만의 꿀팁</span>
                      <span className="review-tip-content">{info.tip}</span>
                    </div>
                  ) : (
                    ''
                  )}

                  <div className="review-post">{info.content}</div>
                  <div className="review-like-button">
                    <button className="review-button">
                      {/*{alllikeData.includes(info.id) ? (*/}
                      {/*  <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />*/}
                      {/*) : (*/}
                      {/*  <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />*/}
                      {/*)}*/}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))
        : data
            .sort((a, b) => a.star - b.star)
            .map((info, index) => (
              <div className="review-content" key={index}>
                {' '}
                <div className="review-user-image">
                  <FontAwesomeIcon icon={faCircleUser} className="review-user-icon" color="#BDBDBD" />
                </div>
                <div className="review-user">
                  <div className="review-list-icon">
                    <ReviewModal />
                  </div>
                  <div className="review-user-name-star">
                    <div className="review-user-name">{info.authorName}</div>
                    <ReviewStar star={info.star} />
                  </div>

                  {info.tip ? (
                    <div className="review-tip-wrapper">
                      <span className="review-tip-name">나만의 꿀팁</span>
                      <span className="review-tip-content">{info.tip}</span>
                    </div>
                  ) : (
                    ''
                  )}

                  <div className="review-post">{info.content}</div>
                  <div className="review-like-button">
                    <button className="review-button" onClick={() => onToggle(info.id)}>
                      {alllikeData.includes(info.id) ? (
                        <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C"/>
                      ) : (
                        <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" />
                      )}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))}
    </>
  );
}
