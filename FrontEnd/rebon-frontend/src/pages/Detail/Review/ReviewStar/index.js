import React, { useState, useLayoutEffect } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
// import SearchBar from './SearchBar';
import { Link } from 'react-router-dom';

import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
import { MoreVertical, Trash, Edit, AlertOctagon, X } from 'react-feather';
import { faCircleUser, faShareNodes, faEllipsisVertical } from '@fortawesome/free-solid-svg-icons';
import { faHeart, faPenToSquare } from '@fortawesome/free-regular-svg-icons';
import ReviewDropdown from '../ReviewDropdown';
import ReviewModal from '../ReviewModal';
import '../../../../styles/review.css';

export default function ReviewStar({ star }) {
  return (
    <div className="review-star-wrapper">
      {star === 0.0 ? (
        <>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
        </>
      ) : star === 1.0 ? (
        <>
          <label className="review-star-point">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
        </>
      ) : star === 2.0 ? (
        <>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
        </>
      ) : star === 3.0 ? (
        <>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star">★</label>
          <label className="review-star">★</label>
        </>
      ) : star === 4.0 ? (
        <>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star">★</label>
        </>
      ) : star === 5.0 ? (
        <>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
        </>
      ) : (
        <>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
          <label className="review-star-point">★</label>
        </>
      )}
    </div>
  );
}
