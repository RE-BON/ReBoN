import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import SearchBar from './../../../../components/Header/SearchModal/SearchBar';
import './../../../../styles/review-modal.css';
import { Link } from 'react-router-dom';

import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
import { MoreVertical, Trash, Edit, AlertOctagon, X } from 'react-feather';
export default function ReviewModal() {
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);
  const [report, setReport] = useState([]);

  function toggleModal(e) {
    console.log('hihihi');
    setOpacity(0);
    setIsOpen(!isOpen);
  }

  function afterOpen() {
    setTimeout(() => {
      setOpacity(1);
    }, 100);
  }

  function beforeClose() {
    return new Promise((resolve) => {
      setOpacity(0);
      setTimeout(resolve, 300);
    });
  }

  const handleClickReport = (e) => {
    console.log(e.target.value);
    setReport(e.target.value);
  };

  const CustomToggle = React.forwardRef(({ children, onClick }, ref) => (
    <Link
      to=""
      ref={ref}
      onClick={(e) => {
        e.preventDefault();
        onClick(e);
      }}
    >
      {children}
    </Link>
  ));

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  return (
    <ModalProvider backgroundComponent={FadingBackground}>
      {/* <div onClick={toggleModal}>
        <SearchBar />
      </div> */}

      <Dropdown>
        <Dropdown.Toggle as={CustomToggle}>
          <MoreVertical size="15px" className="text-secondary" />
        </Dropdown.Toggle>
        <Dropdown.Menu align="end">
          {/* <Dropdown.Header>SETTINGS</Dropdown.Header> */}
          <Dropdown.Item eventKey="1">
            {' '}
            {/* <Edit size="18px" className="dropdown-item-icon" /> 활동내역 보기 */}
            <div onClick={toggleModal}>
              <AlertOctagon size="18px" className="dropdown-item-icon" /> <p>신고하기</p>
            </div>
          </Dropdown.Item>
          <Dropdown.Item eventKey="2">
            {' '}
            <X size="18px" className="dropdown-item-icon" /> 닫기
          </Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>

      <StyledModal
        isOpen={isOpen}
        afterOpen={afterOpen}
        beforeClose={beforeClose}
        onBackgroundClick={toggleModal}
        onEscapeKeydown={toggleModal}
        opacity={opacity}
        backgroundProps={{ opacity }}
      >
        <div className="review-modal-wrapper">
          <div className="review-modal-close-wrapper">
            <button onClick={toggleModal} className="review-modal-close">
              <FontAwesomeIcon icon={faXmark} />
            </button>
          </div>

          <div className="review-report-cause">신고 사유를 선택해주세요.</div>

          <div className="review-report-cause-wrapper">
            <label className="review-report-cause-list">
              <input type="radio" value="4" checked={report === '4'} onChange={handleClickReport} className="review-report-radio" />
              <p className="review-report-radio-content">이미지, 사진, 콘텐츠를 도용했어요.</p>
            </label>
            <label className="review-report-cause-list">
              <input type="radio" value="2" checked={report === '2'} onChange={handleClickReport} className="review-report-radio" />
              <p className="review-report-radio-content">불법적인 내용이에요.</p>
            </label>
            <label className="review-report-cause-list">
              <input type="radio" value="3" checked={report === '3'} onChange={handleClickReport} className="review-report-radio" />
              <p className="review-report-radio-content">욕설을 했어요.</p>
            </label>
          </div>
          <div className="review-modal-report-button-wrapper">
            <button className="review-modal-report-button">신고하기</button>
          </div>
        </div>
      </StyledModal>
    </ModalProvider>
  );
}

const StyledModal = Modal.styled`
  width: 22rem;
  height: 23rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
