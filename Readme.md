# Projeto: Criptografia e Descriptografia com AES e Base64

**Desenvolvido por:** Geraldo Lucas Bezerra Rocha  
**Disciplina:** IMD0703 - SEGURANÇA DE REDES - T02  
**Universidade Federal do Rio Grande do Norte (UFRN)**  
**Professor:** Anderson Claudio Rodrigues Da Silva  

## 📄 Descrição

Este projeto implementa **criptografia simétrica** usando o **algoritmo AES** com o modo **CBC (Cipher Block Chaining)** e **codificação Base64** para facilitar o armazenamento e visualização dos dados criptografados.

A chave utilizada é derivada de uma senha textual simples através do algoritmo **SHA-256**, garantindo uma chave de 256 bits. O sistema adiciona padding manualmente para garantir que os dados estejam alinhados com o tamanho do bloco do AES.

## ✅ Funcionalidades

- Geração de chave segura a partir de senha usando SHA-256
- Criptografia AES (modo CBC)
- Descriptografia com remoção de padding
- Codificação e decodificação usando Base64
- Impressão do texto original, criptografado e descriptografado

## 🧠 O que foi feito

- O programa Java implementa **criptografia AES** com chave derivada via **SHA-256** a partir de uma senha simples fornecida pelo usuário.
- Um método manual de **padding** foi adicionado para garantir que o texto seja múltiplo de 16 bytes, conforme exigido pelo algoritmo AES.
- O **IV (vetor de inicialização)** é gerado aleatoriamente a cada execução, o que aumenta a segurança do algoritmo.
- A saída criptografada é codificada em **Base64**, facilitando o armazenamento e a exibição dos dados cifrados.
- Um método correspondente de **remoção do padding** é aplicado após a descriptografia para recuperar o texto original.
- O código está encapsulado em uma classe chamada `AESCipher`, e pode ser executado facilmente pelo método `main`.
