import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import SearchBar from './SearchBar';
import Tags from '../../../pages/Search/Tags';
import AutoCompletes from '../../../pages/Search/AutoCompletes';
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
    console.log(2222);
  }

  function beforeClose() {
    return new Promise((resolve) => {
      setOpacity(0);
      setTimeout(resolve, 300);
    });
  }

  //여기서 계속 새로고침 되는 것이 문젠데..
  const [word, setWord] = useState('');
  const onChangeword = (e) => {
    setWord(e.target.value);
  };

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  const [autoComState, setAutoComState] = useState('none');
  const onChangeState = () => {
    setAutoComState('block');
  };

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
        <div className="header-modal-wrapper">
          <div className="header-modal-wrapper-search">
            <div className="header-search-bar-wrapper">
              <img src="../../../../image/search-icon.png" alt="header-search-icon" />
              <input placeholder="가고 싶은 지역을 입력해주세요." value={word} onChange={onChangeword} onClick={onChangeState} />
            </div>
          </div>
          <div className="header-autoCom" style={{ display: autoComState }}>
            <AutoCompletes word={word} />
          </div>
          <div className="header-tag">
            <Tags />
          </div>
        </div>
      </StyledModal>
    </ModalProvider>
  );
}

const StyledModal = Modal.styled`
  position:absolute;
  top: -1%;
  width: 100%;
  padding : 1% 20%;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;
  @media (max-width: 767px) {
    height:100%;
    padding : 1% 2%; 
  } 
  @media (min-width: 768px) and (max-width: 1199px) {
    padding-bottom :  5vh; 
  }

  @media (min-width: 1200px) {
    padding-bottom :  5vh; 
  }
`;
