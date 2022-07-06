import React, { useState, useLayoutEffect } from 'react';
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
// import { solid, regular, brands } from '@fortawesome/fontawesome-svg-core/import.macro'; // <-- import styles to be used

export default function ReviewContent({ data, sort }) {
  const [alllikeData, setAllLikeData] = useState([]);
  // var ID = parseInt(window.sessionStorage.getItem('id'));

  const onToggle = (reviewID) => {
    if (alllikeData.includes(reviewID)) {
      // deleteLike(ID, reviewID);
    } else {
      // addLike(ID, reviewID);
    }
  };

  const deleteLike = async (userID, reviewID) => {
    setAllLikeData(alllikeData.filter((item) => item !== reviewID));

    var params = new URLSearchParams();
    params.append('user_id', userID);
    params.append('review_id', reviewID);
    // const response = await axios.post(process.env.REACT_APP_RESTAPI_HOST + 'like/delete', params);
  };

  const addLike = async (userID, reviewID) => {
    setAllLikeData((prevState) => [...prevState, reviewID]);

    var params = new URLSearchParams();
    params.append('user_id', userID);
    params.append('review_id', reviewID);
    // const response = await axios.post(process.env.REACT_APP_RESTAPI_HOST + 'like/add', params);
  };

  return (
    <>
      {sort === '1'
        ? data
            .sort((a, b) => b.empathyCount - a.empathyCount)
            .map((info) => (
              <div className="review-content">
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
                      {alllikeData.includes(info.id) ? (
                        <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      ) : (
                        <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      )}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))
        : sort === '2'
        ? data
            .sort((a, b) => b.id - a.id)
            .map((info) => (
              <div className="review-content">
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
                      {alllikeData.includes(info.id) ? (
                        <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      ) : (
                        <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      )}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))
        : sort === '3'
        ? data
            .sort((a, b) => b.star - a.star)
            .map((info) => (
              <div className="review-content">
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
                      {alllikeData.includes(info.id) ? (
                        <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      ) : (
                        <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      )}
                    </button>
                    <span className="review-like-num">{info.empathyCount}</span>
                  </div>
                </div>
              </div>
            ))
        : data
            .sort((a, b) => a.star - b.star)
            .map((info) => (
              <div className="review-content">
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
                      {alllikeData.includes(info.id) ? (
                        <FontAwesomeIcon icon={regularHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
                      ) : (
                        <FontAwesomeIcon icon={solidHeart} className="review-like-icon" size="1x" color="#FF6B6C" onClick={onToggle(info.id)} />
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
