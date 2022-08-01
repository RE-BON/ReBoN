import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
// import SearchBar from './../../../../components/Header/SearchModal/SearchBar';
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
      <Dropdown>
        <Dropdown.Toggle as={CustomToggle}>
          <MoreVertical size="15px" className="text-secondary" />
        </Dropdown.Toggle>
        <Dropdown.Menu align="end">
          <Dropdown.Item eventKey="1">
            <Link to="/modify" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div onClick={toggleModal}>
                <Edit size="18px" className="dropdown-item-icon" /> <p className="review-modal-report">리뷰수정</p>
              </div>
            </Link>
          </Dropdown.Item>
          <Dropdown.Item eventKey="2">
            <div onClick={toggleModal}>
              <Trash size="18px" className="dropdown-item-icon" /> <p className="review-modal-report">리뷰삭제</p>
            </div>
          </Dropdown.Item>
          <Dropdown.Item eventKey="3">
            <X size="18px" className="dropdown-item-icon" /> <p className="review-modal-close">닫기</p>
          </Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
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
