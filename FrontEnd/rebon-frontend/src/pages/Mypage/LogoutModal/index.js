import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import LogoutIcon from '@mui/icons-material/Logout';
import '../../../styles/logout-modal.css';
export default function LogoutModal() {
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

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  function Logout(e) {
    window.localStorage.setItem('Login', false);
  }
  return (
    <ModalProvider backgroundComponent={FadingBackground}>
      <div onClick={toggleModal}>로그아웃</div>

      <StyledModal
        isOpen={isOpen}
        afterOpen={afterOpen}
        beforeClose={beforeClose}
        onBackgroundClick={toggleModal}
        onEscapeKeydown={toggleModal}
        opacity={opacity}
        backgroundProps={{ opacity }}
      >
        <div className="logout-wrapper">
          <button className="close" onClick={toggleModal}>
            <FontAwesomeIcon icon={faXmark} />
          </button>
          <div className="logout-icon">
            <LogoutIcon fontSize="large" />
          </div>
          <div className="logout-notice">로그아웃 하시겠습니까?</div>
          <Link to="/logout" style={{ color: 'inherit', textDecoration: 'none' }}>
            <button className="logout-button" onClick={Logout}>
              확인
            </button>
          </Link>
        </div>
      </StyledModal>
    </ModalProvider>
  );
}

const StyledModal = Modal.styled`
  width: 23rem;
  height: 20rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
