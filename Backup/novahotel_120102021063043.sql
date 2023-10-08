-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: novahotel_1
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_acompanhantes`
--

DROP TABLE IF EXISTS `tbl_acompanhantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_acompanhantes` (
  `acompanhantes_id` int(11) NOT NULL,
  `acompanhantes_nome` varchar(200) NOT NULL,
  `acompanhantes_cpf` varchar(20) NOT NULL,
  `acompanhantes_hospedagem_id` int(11) NOT NULL,
  KEY `acompanhantes_hospedagem_id` (`acompanhantes_hospedagem_id`),
  CONSTRAINT `acompanhantes_hospedagem_id` FOREIGN KEY (`acompanhantes_hospedagem_id`) REFERENCES `tbl_hospedagem` (`hospedagem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_acompanhantes`
--

LOCK TABLES `tbl_acompanhantes` WRITE;
/*!40000 ALTER TABLE `tbl_acompanhantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_acompanhantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cabecalho`
--

DROP TABLE IF EXISTS `tbl_cabecalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cabecalho` (
  `cabecalho_id` int(11) NOT NULL AUTO_INCREMENT,
  `cabecalho_logo` longblob DEFAULT NULL,
  `cabecalho_linha_1` varchar(500) DEFAULT NULL,
  `cabecalho_linha_2` varchar(500) DEFAULT NULL,
  `cabecalho_linha_3` varchar(500) DEFAULT NULL,
  `cabecalho_linha_4` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`cabecalho_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cabecalho`
--

LOCK TABLES `tbl_cabecalho` WRITE;
/*!40000 ALTER TABLE `tbl_cabecalho` DISABLE KEYS */;
INSERT INTO `tbl_cabecalho` VALUES (1,'����\0JFIF\0\0\0\0\0\0��\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342��\0C			\r\r2!!22222222222222222222222222222222222222222222222222��\0\0�\0�\"\0��\0\0\0\0\0\0\0\0\0\0\0	\n��\0�\0\0\0}\0!1AQa\"q2���#B��R��$3br�	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz���������������������������������������������������������������������������\0\0\0\0\0\0\0\0	\n��\0�\0\0w\0!1AQaq\"2�B����	#3R�br�\n$4�%�\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz��������������������������������������������������������������������������\0\0\0?\0�z(���(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��\0(��L�tG�#ԵK���c�|��6�������^��\0��^�^�e�7k��(���>�\0�������a�W��?��\0����O�g�N��>���^�\'�\0��}�����\0�O�\0���?�=��9J+wV�ÐZ�]]\\�3c¨�9Q���xN-Dڶ��ǧ��y B���@K9����⛨�n�f۲9�+�Ӵ}2���\Z����5��+v�H��V��m��}��c��B�n�����I6�Rr�I>���\Z�r��)%s���;o\nMe�^�[�c��Ϩ�*��|�\0Ak����\0�n���������]g�|�\0A[����\0�_��/��ן���\0�t���������Q]g�|�\0A[����\0�_��/�������\0�t{O\'���G%Et>%Ѭ4�}>{	f�;�g�)0�c���\\�\\d����\\]�QEB\n(��\n(��\n(��\n(��\n+�~\\?�E7�8�nq�N3�U�o����-|)<у��w�}8��W,�ϝ�1�����\'���	c���t��\'�5\0�vT���L�\0����\0�\'��\0�T������M��\0���\0���\0����\0Лu�\0��\0�oQ�����/���\0�$��\0�	���%Cu�\rkI��¶���U%.J88��[�>��Y�t\0$�x�\0�u�����x�+XV��q@�̂m����GA��֔Ԝ����qKI~)R�y�0�C��N���S�S�V:d��Ip��u6�ݔ�$�c��d��o�_^Z�#R�|a���y[K�Dks���\0?ε�V�k\Zi����&�ݬ��cm{2_A�O�e�dP{�`\rk<6�K���M����f��<o&2@\0c���<�n=H��.4�뛍gÉz��`^[A�\"0B����<���x~�4Е�4�_��Hˍ��$\\s��,�ܛ�������(�9![\Zͯ�W�hڒFm�ي�w�D�G9S\Z�8�ڡ��m\"X�?��چ�u=��.�R�\"b!�bN9��Z�\\h�����R���I\"9��\'pn>�`y\'�H�Nj�S�Z��[�>���n�2Ys����.p6���\0\rG3{�\0V��*��$�v�$C|-#�jD����B�Iǭ`<RD@�7L�7)�P�խd��K�&:F�n�\Zj�\n�<c�q�\'��}کo�>�e5���]7]ӛ\"�aq31\0	9b:q]0�%��yJ�r��:�E�x�h�EVQ�0����M��_�\nz��GH�.�:M�D�H �~PT�9���zq[���6��[�\'I�k���m��r6����ZO�QRQ׵�L-Y����\0��@�\0�B��\0���Z֑ya$���f����خ\'��8�kп�bj��\']���\0���\0���Нu�\0��\0�t��?F����\'�����{��)^��\0U�\0�:���\0�7Km�\"�MZ���ÒZ�����70\\��3U\n�ar~(R�NN�߁�W{�hc�6��\0�5�\0�޸*�>x)w9�I8�QZQE\0QE\0QE\0zWÿ��K�\0\\��\0E�p����f�\0�b�@�w�\0\"��趯?�Z����U�K���_��?����*`Q�(���1F(��\n(��G�VY岚{��\'�|���;a��u[+=4Dڀӯa���fhݖD\nT��	��<�ui%ş��4�E��i-�Ѳ�J���r	T\n���5�^xZM3I��Q��Geͬ�Q\\��xS����ǘw3^	�𮵬XiWi%�i�S+�y���$yP���9���~j�[���:�ћ� -�=z�w2i (6��r�#\0&}��Q�������=󵆧>�/,�^7@����|K�X�3k�Tz���co+��{u���e�;x�EsZ��}��$����Ɗ������=I�P��=vзUEh\\�6�o��٭��O��[�M�R������<�X�Q]Q���9�&�&���h��\0+���\'�o��_�\Z+���E�\0�������j�u~�t�6>-�\0��k�\0^K�\0��pU��[�\0�������\0Cz�0�\0�U�6QEndQE\0QE\0QE\0z_ÿ��K�\0\\��\0E�y��\0����&�\0�b�@�w�\0\"��趯83�Y�m�m�Fp{��G��+���g�#�Ï��#��+��\n(��-�<mw����Ct�Ā�\"hC�\'�#�ku���[XO��K}s����ݭ�ѳ�s<Q�Tp1��X�pv�6-;Y��Ƣ��w���*���n0��bv���j�uX�RC�GQ]����l��~ �Yu��Lzd7�H�1)�M�9��\0�y��S���R�t�l�t�P-�-Z���5r�{��^�_Y��C�xV��	\'�Fd��uF+�1?*���lrMe�N��R�ev�\\^����\\�s����]v�$��^!�u9m��~�u��H�&����T����.Ѓm|��	C9�\0x2�U�i��V`O���K\0nf\n��oC�˜�����u�\\Ka����\0CU�(�r�dl��U[��XFY�wWEe�Z�\\��l�6�[�`q��,�D��92\0J vL�R�Ƚ�j�1��F��S��@����7yHg�d�6�y���`\n\0��c�qA���ð��o�+̓���YP��0�G>A�\rqu���Os�����Ԯ	�y��@A\0\"���BF\n���LL(��\0(��\0+���\'�o��_�\Z��V��Z]@��\r�X=�d���_�к��Z�\0�������\0Cz૾���\0#E��y������x�GЪ�\0�aEV�AEPEPEP�|;�\0�?Ŀ�Ϳ�[W�K�\\r���q$��?��\0ʻ��\'�������j�t=n�D�Bw�x�pC�\\p��.�~GL�ycͰ�oE��/L3\r�6LR�p?���V�F�U�{���#p\0裰��\0=j�uF��s�V��ZzF��h�e��3�Qqm<K,39сS���#��EP����-ޝ�K�x��e�����-,D��df\n���=+����\\�|�˳=�Ԋ�=̠d�����$�\0�R�-2Xm�u�<)r �`��o0�r�c)�1R7���\0ٷ�}�����uf�0���;q2�_\"T?Ք�\'�x�1�i~M��Q.��8�ʆY�nr�PT����⠰�j��wg}��Z�z�?���K�x�d�,l�T�bbu$^�;]�^�^֡�ɖ���WM\"���L��\n_` q�}�+�&����ڞ��I�_�Ռ�7LP�bEW�Dǐ��\n�ddT��m?�zn�=�?R�a�Ԣ�:�����\n�v����8<e�)�D�<E�j�x�ĥ�ɒ�!os��Aߘ�P�Y��\0֎��+O����7r^�zmQud�����ep	G����(���4H�+}H꺆./%�Xƞ�,f2�O��d�@��+�|�_T�0x���\ZN�=��H�[1r��č�c�s�s6i�^�m[[���+��	#�r��+S�)���1����}c�ZM��+�����(w=��qJ���y��p%�R��g �i\Zڽ��q�p+�#��S?�|��|���Ԋɮ�I�-�=��f��S��H�~�tc�h^�E$�	tؿ�� �غվ�k��å^��<H~�6�?.�ʧp�g8☊QE\0t~��닆hm@a����w?���:]Ƒ�m*�q�\0/��a���5GB�\rև+y����,p	�G��\0>�>�{q�x�J�����{\'��8Ռ����ch�Y[s����\0#E��y������}�o�F�_��_�\r끥��}[��(����(��\0(��\0(��\0����\'�������j�s��w����*��W�|;�\0�?ğ�Ϳ�[W���\0 ;O����\0A��i?���~GD�\0��R�(����ccq�]�����f,�\0I$\0&���Z��7Y�&V����\nfp������r�1�Po�yw�)UGi?�����ܷA�`\rl�G���X}�v��C$Z��;��<���v�T`�q�7���`\0�!���q��JP��+9�Kg1\\�;��Nx����\\[��]I �b�X0;N��Ì���S�Oo��ν��B��MҦ�.^7[ж�RI�K1�22�p~@��!����M�k��9T��d��1����,g\'���4�&��5������s��m��5U����[���m��&��I\r�uVU>��Xi�wP��z�����\0N�U��׊�Cf�<�Lj�ho-��q���ZxW�	�{�m�Q��B=���;�G]�	c!\"�Tm�y-���!�ޭqc5�6�dp[��TYȰ�0ǝ�y8�zsN��:��2��h!��\\���eHr?xb��e9�6��k�ס���GR��l�1����4�E�C��$��#�*:*�pT�K���\Z�泬]Z�e�6��(_d�K!\rʻG@�N\n�,y�]�J�����[�s[79v������,@�@����b��ڕ����Q��IWlǲI?)�H\0�A5�j��0�t��{�5mZ�Kd�%��n�ݢ����R���p�xsY�Qm=t��X�]��炏�k��r��AȬ�`���;x\"yg��$h����\0\0�{W�����A}=�����\r������Cs�HJ�1�S�NI)�[f*e�to�r�o��#�5�?2+).B���p����KaF�;qESV��?�S66K{�x�Xu�h����~���_��\0���Z��\0�h��\0�5�\0�޸\Z�-�\0��k�\0^k�\0��p5��Q�*��(�̂�(��(��(�K�w�\0\"�?�趯?��@v���7��z����I�\0\\��\0E�p����&�\0�b�Jŗ�����\0>��N�(�u���5��H%x��!db�Xq؂A�\"��k��&p��b�#EE��\0�\0G�\\\Z\0���.���X���Y�D��%3�~U���E=��\"4���Q/@�	8��}I$�I��h��k�jMm8_�m��L�Qo]��x늷�xcXֶ����`�i@s����W�\rN��E��\0��ioc��VUm�3����\0���\'�,t�����=�h�\\H���B=�o}���V�.�����9y�!����,5��T[��f\0���Fx=x���iךeǑ{m-����T�#�z��m-�^H�W<�#���c\'�����\"�<�H����U�� �$!]�m�G���5�_����-�RZny���\r&\r2�٭V���[�|���#\r�3��VAW\\Z��9ڳ��������4�m�Ք̻\ZKi�6+�pJ�q�8���I�i��\"k�ۭB��nf����B��\'�����}(�ޔ\0����\0���~/����>��������~/���_���]=�o��4Z�\0ך�\0�o\\\rw��\0�h��\0�5�\0�޸\Z�?��Uo�0��+s ��(\0��(\0��(�>۽���hʇ�yj[�%�굷�ZA�E{��ĉ�`��џA�We��Zj:X���+���R�>��_�����z��?��#�UNR�V}΅V�*ROC��\0������\0���/�+�\0�և�\0���\0ƫ9u�z���fԯ\Z���B�\'!�;�\\��_�o�_��Q�\0���\0��;��$�-���|=�S�Su�`�@��\0iW!�?\n��^[t�x\\(б#�dr<�κ�o�_��R�\0���\0�ꆭ��Od�j�]�on�)2�,�\0�7ޕ�9�KY+y8ŭ\"��o¶Z���С>c�͑���\r�N1�Zѽ�ѡ�N��xJ��h�Ǟi�Fc�v�}�ڸ*փĺ嵘��U���Ue#h��´�)�t�\0OȈ�)ZǢ�ZE��$���F�Z�e\"\0�0�H���80瘭�iW	�\r&���5����0dNq�8���z�1�)��i%��p�pX���r�ؑ���Zq�o����WO�Wm\\�#�DĘ�rDl;cj���PG,�Ԛz�\0[������Է��.4�����i��C�\n/�#u@H{�m�*��^ԣ�5%�LӴɞ+d����r\n��>���	��Z�~!Mh`�t�R����\r��y|c���\r	v�&����u�K�x�ӳ��DO2 >��?�sQf���\0VU�B=KB����^ ��H<��6�kG>U|}�9��	�Y���43�ZhVѮ�%úT��#��sY�0���\\���Y-�Il��q�h������q��X��櫩�!��nn\"!$��Ϯ=}����>F�T��{��z�Q�;Ȳn��UX�~�\n��l�N	��xw��ƹd�����F$*�h��c���3��k��)\'�8bB�H�QTrI8\0Wik�߈6�oj���w��2rp��Z�r��W�&)JW�����\0���D�\0��\0ƨ�\0��?���߅�\0�U����#�z�?���]U�`��c%��ޡ\r�x��\0m Ԋ�N�vRF���,��\0��?���߅�\0�U-��<B�Λyyw��V���bx�<V?�u�^�EФ�S���22���ڜ�y�:��*����o�֜ou�\nR�k3����\0#E��y�����՛�B�R�e����E]��r������]T����.i9 ��+B�(��(��(��(��N�.u��[ڼi\"]4��$\rð>�_�]o�m����\0�5��X�9&��\0_h�����mo�m����\0�4�D`�Z�{_�\0��B�|����=���\0�U�\0��/����_�@5_����\0���\0��J�v��߀sC���xcQѭ���)\"\'k4,N߮@�ZZ�tإ�}j��岉��{b2�0+��#��{W!EѴ�����ldǋ���[T�\r��;�7A��h�(��\Z4���\r`����Z��ش�Y��kǾ�I���z�\0:�zs�QS\Z	J�U�du0�+Y��n��yF⳻o�x<ԃ�:���Y�\0�m�\0��%EU���4;p��\"���!��\0���\0�������&��(��9���;�A������\0���\0��k,0ז�z�\0��>�\\���<{~\'c�[w���Ki\n��&F+А�8������G�6&r�w\n(�����(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(\0��(��','HOTEL AVENIDA - (65) 3266-1624','','','');
/*!40000 ALTER TABLE `tbl_cabecalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cliente`
--

DROP TABLE IF EXISTS `tbl_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cliente` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_nome` varchar(200) NOT NULL,
  `cliente_RG` varchar(20) DEFAULT NULL,
  `cliente_CPF` varchar(20) DEFAULT NULL,
  `cliente_endereco_logradouro` varchar(200) NOT NULL,
  `cliente_endereco_numero` varchar(200) NOT NULL,
  `cliente_endereco_bairro` varchar(200) NOT NULL,
  `cliente_endereco_cidade` varchar(200) NOT NULL,
  `cliente_endereco_estado` varchar(2) NOT NULL,
  `cliente_telefone` varchar(20) NOT NULL,
  `cliente_endereco_complemento` varchar(200) DEFAULT NULL,
  `cliente_endereco_cep` varchar(100) NOT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cliente`
--

LOCK TABLES `tbl_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hospedagem`
--

DROP TABLE IF EXISTS `tbl_hospedagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hospedagem` (
  `hospedagem_id` int(11) NOT NULL AUTO_INCREMENT,
  `hospedagem_cliente_id` int(11) NOT NULL,
  `hospedagem_cliente_nome` varchar(200) NOT NULL,
  `hospedagem_quarto_id` int(11) NOT NULL,
  `hospedagem_quarto_numero` varchar(100) NOT NULL,
  `hospedagem_usuario_id` int(11) NOT NULL,
  `hospedagem_usuario_nome` varchar(200) NOT NULL,
  `hospedagem_status` varchar(100) NOT NULL,
  `hospedagem_checkin_data` date DEFAULT NULL,
  `hospedagem_checkin_hora` time DEFAULT NULL,
  `hospedagem_checkout_data` date DEFAULT NULL,
  `hospedagem_checkout_hora` time DEFAULT NULL,
  `hospedagem_valor_total` double DEFAULT NULL,
  `hospedagem_valor_diaria` double NOT NULL,
  `hospedagem_forma_pagamento` varchar(100) DEFAULT NULL,
  `hospedagem_veiculo_placa` varchar(100) DEFAULT NULL,
  `hospedagem_veiculo_marca` varchar(100) DEFAULT NULL,
  `hospedagem_total_diarias` double DEFAULT NULL,
  `hospedagem_quantidade_diarias` double DEFAULT NULL,
  `hospedagem_valor_desconto` double DEFAULT NULL,
  PRIMARY KEY (`hospedagem_id`),
  KEY `hospedagem_cliente_id` (`hospedagem_cliente_id`),
  KEY `hospedagem_quarto_id` (`hospedagem_quarto_id`),
  KEY `hospedagem_usuario_id` (`hospedagem_usuario_id`),
  CONSTRAINT `hospedagem_cliente_id` FOREIGN KEY (`hospedagem_cliente_id`) REFERENCES `tbl_cliente` (`cliente_id`),
  CONSTRAINT `hospedagem_quarto_id` FOREIGN KEY (`hospedagem_quarto_id`) REFERENCES `tbl_quarto` (`quarto_id`),
  CONSTRAINT `hospedagem_usuario_id` FOREIGN KEY (`hospedagem_usuario_id`) REFERENCES `tbl_usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hospedagem`
--

LOCK TABLES `tbl_hospedagem` WRITE;
/*!40000 ALTER TABLE `tbl_hospedagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produto`
--

DROP TABLE IF EXISTS `tbl_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_produto` (
  `produto_id` int(11) NOT NULL AUTO_INCREMENT,
  `produto_nome` varchar(200) NOT NULL,
  `produto_valor` double NOT NULL,
  `produto_quantidade` int(100) NOT NULL,
  PRIMARY KEY (`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produto`
--

LOCK TABLES `tbl_produto` WRITE;
/*!40000 ALTER TABLE `tbl_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produtos_hospedagem`
--

DROP TABLE IF EXISTS `tbl_produtos_hospedagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_produtos_hospedagem` (
  `produtos_hospedagem_id` int(11) NOT NULL,
  `produtos_hospedagem_produtos_id` int(11) NOT NULL,
  `produtos_hospedagem_nome` varchar(100) NOT NULL,
  `produtos_hospedagem_produtos_valor` double NOT NULL,
  `produtos_hospedagem_quantidade` int(11) NOT NULL,
  `produtos_hospedagem_hospedagem_id` int(11) NOT NULL,
  KEY `produtos_hospedagem_produtos_id` (`produtos_hospedagem_produtos_id`),
  KEY `produtos_hospedagem_hospedagem_id` (`produtos_hospedagem_hospedagem_id`),
  CONSTRAINT `produtos_hospedagem_hospedagem_id` FOREIGN KEY (`produtos_hospedagem_hospedagem_id`) REFERENCES `tbl_hospedagem` (`hospedagem_id`),
  CONSTRAINT `produtos_hospedagem_produtos_id` FOREIGN KEY (`produtos_hospedagem_produtos_id`) REFERENCES `tbl_produto` (`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produtos_hospedagem`
--

LOCK TABLES `tbl_produtos_hospedagem` WRITE;
/*!40000 ALTER TABLE `tbl_produtos_hospedagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_produtos_hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_quarto`
--

DROP TABLE IF EXISTS `tbl_quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_quarto` (
  `quarto_id` int(11) NOT NULL AUTO_INCREMENT,
  `quarto_numero` varchar(10) NOT NULL,
  `quarto_andar` varchar(10) NOT NULL,
  `quarto_descricao` varchar(200) DEFAULT NULL,
  `quarto_tipo` varchar(100) NOT NULL,
  `quarto_itens_dispo` varchar(200) DEFAULT NULL,
  `quarto_status` varchar(100) NOT NULL,
  PRIMARY KEY (`quarto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_quarto`
--

LOCK TABLES `tbl_quarto` WRITE;
/*!40000 ALTER TABLE `tbl_quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_quarto_acompanhantes`
--

DROP TABLE IF EXISTS `tbl_quarto_acompanhantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_quarto_acompanhantes` (
  `quarto_acompanhantes_id` int(11) NOT NULL,
  `quarto_acompanhantes_quarto_id` int(11) NOT NULL,
  `quarto_cama_quantidade_acompanhantes` int(11) NOT NULL,
  `quarto_acompanhantes_valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_quarto_acompanhantes`
--

LOCK TABLES `tbl_quarto_acompanhantes` WRITE;
/*!40000 ALTER TABLE `tbl_quarto_acompanhantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_quarto_acompanhantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_nome` varchar(500) NOT NULL,
  `usuario_login` varchar(100) NOT NULL,
  `usuario_senha` varchar(100) DEFAULT NULL,
  `usuario_tipo` varchar(100) NOT NULL,
  `usuario_inativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-20 18:30:44
