<?php

require_once 'MyPDO.php';

/**
 * La classe App défini les fondamentaux de l'application (connexion base de données, routeur, ...).
 * Elle implémente le patron singleton.
 * @remarks Pour fonctionner correctement, la classe a besoin que le fichier app/config/config.php existe
 */
class App
{
    /**
     * @var App
     */
    private static $instance;

    /**
     * @var PDO connexion entre PHP et le serveur de base de données
     */
    private $db;

    /**
     * App constructor.
     */
    private function __construct()
    {
        $this->db = new MyPDO();
    }

    /**
     * @return App instance courante de l'application
     */
    public static function getInstance(): App
    {
        if (!static::$instance) {
            static::$instance = new App();
        }
        return static::$instance;
    }

    /**
     * @return PDO connexion entre PHP et le serveur de base de données
     */
    public function getDb(): PDO
    {
        return $this->db;
    }
}
