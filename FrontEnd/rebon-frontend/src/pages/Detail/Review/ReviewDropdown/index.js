import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
// import SearchBar from './SearchBar';
import { Link } from 'react-router-dom';

import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
import { MoreVertical, Trash, Edit, AlertOctagon, X } from 'react-feather';

import '../../../../styles/header-search-modal.css';

export default function ReviewDropdown() {
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);

  function toggleModal(e) {
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
    <Dropdown>
      <Dropdown.Toggle as={CustomToggle}>
        <MoreVertical size="15px" className="text-secondary" />
      </Dropdown.Toggle>
      <Dropdown.Menu align="end">
        {/* <Dropdown.Header>SETTINGS</Dropdown.Header> */}
        <Dropdown.Item eventKey="1">
          {' '}
          {/* <Edit size="18px" className="dropdown-item-icon" /> 활동내역 보기 */}
          <AlertOctagon size="18px" className="dropdown-item-icon" onClick={toggleModal} /> 신고하기
        </Dropdown.Item>
        <Dropdown.Item eventKey="2">
          {' '}
          <X size="18px" className="dropdown-item-icon" /> 닫기
        </Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
  );
}

const StyledModal = Modal.styled`
  width: 30rem;
  height: 30rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
