import React, { useState } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { faXmark } from '@fortawesome/free-solid-svg-icons';
// import SearchBar from './../../../../components/Header/SearchModal/SearchBar';
import '../../../styles/header-modal.css';

import { Dropdown, Image, Row, Col, Table, Button } from 'react-bootstrap';
export default function HeaderModal() {
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
          <img src="/image/user.png" alt="user" className="header-modal-img" />
        </Dropdown.Toggle>
        <Dropdown.Menu align="end">
          <Dropdown.Item eventKey="1">
            <Link to="/mypage" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div onClick={toggleModal}>
                <img src="/image/user.png" alt="user" className="header-modal-user" />
                MY
              </div>
            </Link>
          </Dropdown.Item>
          <Dropdown.Item eventKey="2">
            <Link to="/logout" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div onClick={toggleModal}>
                <img src="/image/logout-black.png" alt="user" className="header-modal-logout" />
                로그아웃
              </div>
            </Link>
          </Dropdown.Item>
          <Dropdown.Item eventKey="3">
            <Link to="/mypage/withdrawal" style={{ color: 'inherit', textDecoration: 'none' }}>
              <img src="/image/withdrawal.png" alt="user" className="header-modal-withdrawal" />
              회원탈퇴
            </Link>
          </Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
    </ModalProvider>
  );
}
