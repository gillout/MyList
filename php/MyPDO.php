<?php

class MyPDO extends PDO {

    private $sgbd = 'mysql';
    private $db = '';
    private $host = '';
    private $user = '';
    private $password = '19690512';

    public function __construct() {
        $sgbdHost = $this->sgbd . ':dbname=' . $this->db . ';host=' . $this->host;
        parent::__construct($sgbdHost, $this->user, $this->password, [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION, PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8']);
    }

}
