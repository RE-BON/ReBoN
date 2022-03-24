import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import SearchBar from './SearchBar';
import '../../../styles/header-search-modal.css';

export default function SearchModal() {
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

  return (
    <ModalProvider backgroundComponent={FadingBackground}>
      <div onClick={toggleModal}>
        <SearchBar />
      </div>
      <StyledModal
        isOpen={isOpen}
        afterOpen={afterOpen}
        beforeClose={beforeClose}
        onBackgroundClick={toggleModal}
        onEscapeKeydown={toggleModal}
        opacity={opacity}
        backgroundProps={{ opacity }}
      >
        <div className="modal-wrapper">
          <div className="modal-wrapper-search">
            <SearchBar />
            <button onClick={toggleModal}>
              <FontAwesomeIcon icon={faXmark} />
            </button>
          </div>

          <ul className="search-list">
            <li>장성동</li>
            <li>잔치국수</li>
            <li>장수동</li>
          </ul>

          <ul className="tag-list">
            <hr />
            <div>추천 태그</div>
            <li> 영일대</li>
            <li>양덕</li>
            <li>구룡포</li>
            <li>칠포해수욕장</li>
            <li>영일대</li>
            <li>양덕</li>
            <li>구룡포</li>
            <li>칠포해수욕장</li>

            <li>구룡포</li>
            <li>칠포해수욕장</li>
          </ul>
        </div>
      </StyledModal>
    </ModalProvider>
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
