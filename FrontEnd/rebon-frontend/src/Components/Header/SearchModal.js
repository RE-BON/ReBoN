import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import SearchBar from './SearchBar';

export default function FancyModalButton() {
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);

  function toggleModal(e) {
    // open하는 함수
    setOpacity(0);
    setIsOpen(!isOpen);
  }

  function afterOpen() {
    // 시간차 두고 open, 불투명하게 하는 함수
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
        <Wrapper>
          <div class="searchArea">
            <SearchBar />
            <Btn onClick={toggleModal}>
              <FontAwesomeIcon icon={faXmark} className="xmark" />
            </Btn>
          </div>

          <ContentList>
            <Content>장성동</Content>
            <Content>잔치국수</Content>
            <Content>장수동</Content>
          </ContentList>

          <Tags>
            <hr />
            <div>추천 태그</div>
            <Tag>영일대</Tag>
            <Tag>양덕</Tag>
            <Tag>구룡포</Tag>
            <Tag>칠포해수욕장</Tag>
            <Tag>영일대</Tag>
            <Tag>양덕</Tag>
            <Tag>구룡포</Tag>
            <Tag>칠포해수욕장</Tag>
          </Tags>
        </Wrapper>
      </StyledModal>
    </ModalProvider>
  );
}

const FadingBackground = styled(BaseModalBackground)`
  opacity: ${(props) => props.opacity};
  transition: all 0.3s ease-in-out;
`;

const StyledModal = Modal.styled`
  width: 30rem;
  height: 30rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;

const Wrapper = styled.div`
  display: grid;
  grid-template-rows: repeat(3, 80px);

  .searchArea {
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

const Btn = styled.button`
  background-color: rgba(0, 0, 0, 0.3);
  color: white;
  border: none;

  margin-left: 10px;
  border-radius: 50%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
`;

const ContentList = styled.ul`
  margin: auto;
  width: 90%;
`;

const Content = styled.li`
  padding: 10px 10px;
  border-radius: 5px;
  color: rgba(0, 0, 0, 0.5);
  font-weight: bolder;
  &:hover {
    background-color: rgba(0, 0, 0, 0.1);
    color: black;
  }
`;

const Tags = styled.ul`
  font-size: 15px;
  padding: 0 5px;
  margin-top: 60px;
  div {
    color: rgba(0, 0, 0, 0.3);
    grid-column: 1 / 5;
    margin-bottom: 10px;
  }
`;

const Tag = styled.li`
  width: fit-content;
  padding: 7px 10px;
  margin: 10px;
  float: left;
  border: 1px solid black;
  border-radius: 50px;
  font-weight: bolder;
  &:hover {
    color: white;
    background-color: black;
  }
`;
