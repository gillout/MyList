<?php

require_once 'App.php';
require_once 'CategoryManager.php';
require_once 'ProductManager.php';
require_once 'StoreManager.php';

$db = App::getInstance()->getDb();

define ("NO_ID", "L'id n'a pas été fourni");
define ("NO_EXIST_ID", "L'id n'existe pas");
define ("NO_CORRECT_PARAM_NEED_ID", "Paramètre non correcte, veuillez fournir un id");
define ("PRODS", "produit(s)" . "<br>");
define ("CATS", "categorie(s)" . "<br>");
define ("MAGS", "magasin(s)" . "<br>");
define ("PROD", "produit");
define ("CAT", "categorie");
define ("MAG", "magasin");
define ("BOTH", "both");
define ("ORDER", "order");

// Récupération des données envoyées au format JSON à partir d'une application
// input est une chaîne de caractères que l'on convertit en objet JSON
$json = file_get_contents("php://input");

file_put_contents("log.txt", $json);

// Décodage du JSON
$input = json_decode($json);

// Récupération du verbe HTTP
$method = $_SERVER["REQUEST_METHOD"];

$params = [];
// Traitement de l'URL pour récupérer les données
if (!empty($_SERVER["PATH_INFO"])) {
    $params = explode("/", $_SERVER["PATH_INFO"]);
}

$display = "";
if (empty($params) || !isset($params[1])) {
    $display = 'Aucun paramètre n\'a été fourni';
} elseif ($params[1] !== PROD && !$params[1] !== CAT && $params[1] !== MAG) {
    $display = 'Paramètre non correcte, veuillez fournir "' . PROD . '" ou "' . CAT . '" ou "' . MAG . '"';
} else {
// Le traitement sera fait en fonction du type de la requête HTTP

    switch ($method) {
        case "POST":
            $display = "Ajout ";

            if ($params[1] === PROD) {
                $display = PRODS;

                $productManager = new ProductManager($db);
                $productManager->create($db, $input);
            } elseif ($params[1] === CAT) {
                $display = CATS;

                $categoryManager = new CategoryManager($db);
                $categoryManager->create($db, $input);
            } elseif ($params[1] === MAG) {
                $display = MAGS;

                $storeManager = new StoreManager($db);
                $storeManager->create($db, $input);
            }
            break;
        case "PUT":
            $display = 'Mise à jour ';

            if ($params[1] === PROD) {
                $display = PRODS;

                $productManager = new ProductManager($db);
                $productManager->update($db, $input);
            } elseif ($params[1] === CAT) {
                $display = CATS;

                $categoryManager = new CategoryManager($db);
                $categoryManager->update($db, $input);
            } elseif ($params[1] === MAG) {
                $display = MAGS;

                $storeManager = new StoreManager($db);
                $storeManager->update($db, $input);
            }
            break;
        case "DELETE":
            if (isset($params[2])) {
                $display = 'Suppression ';

                if (is_numeric($params[2])) {

                    if ($params[1] === PROD) {
                        $productManager = new ProductManager($db);
                        $display = PRODS . $productManager->delete($db, (int)$params[2]);
                    } elseif ($params[1] === CAT) {
                        $categoryManager = new CategoryManager($db);
                        $display = CATS . $categoryManager->delete($db, (int)$params[2]);
                    } elseif ($params[1] === MAG) {
                        $storeManager = new StoreManager($db);
                        $display = MAGS . $storeManager->delete($db, (int)$params[2]);
                    }

                } else {
                    $display = NO_CORRECT_PARAM_NEED_ID;
                }

            } else {
                $display = NO_ID;
            }
            break;
        case "GET":
            $display = 'Affichage ';

            if ($params[1] === PROD) {
                $productManager = new ProductManager($db);

                if (isset($params[2])) {
                    $display = PRODS;

                    switch ($params[2]) {
                        case ORDER:
                            if (isset($params[3])) {

                                if ($params[3] === CAT) {
                                    $display .= " triés par " . $params[3] . "<br>";

                                    $productManager->getAllOrderByCategory($db);
                                } elseif ($params[3] === MAG) {
                                    $productManager->getAllOrderByStore($db);
                                } else {
                                    $display = 'Paramètre non correcte, après "' . ORDER . '" veuillez fournir "' . CAT . '" ou "' . MAG .'"';
                                }

                            } else {
                                $productManager->getAll($db);
                            }
                            break;
                        case CAT:
                            $display = " par " . CAT . "<br>";
                            $display = (isset($params[3]) && is_numeric($params[3])) ? $productManager->getAllByCategory($db, (int)$params[3]) : NO_ID;
                            break;
                        case MAG:
                            $display = " par " . MAG . "<br>";
                            $display = (isset($params[3]) && is_numeric($params[3])) ? $productManager->getAllByStore($db, (int)$params[3]) : NO_ID;
                            break;
                        case BOTH:
                            $display = " par " . CAT . " et " . MAG . "<br>";
                            $display = (isset($params[3]) && isset($params[4]) && is_numeric($params[3]) && is_numeric($params[4]))  ? $productManager->getAllByCategoryAndStore($db, (int)$params[3], (int)$params[4]) : 'Veuillez fournir deux ids';
                            break;
                        default:
                            if (is_numeric($params[2])) {
                                $result = $productManager->findById($db, (int)$params[2]);
                                $display = $result ? $result : NO_EXIST_ID;
                            } else {
                                $display = NO_CORRECT_PARAM_NEED_ID .', ou "' . BOTH . '" et deux ids';
                            }
                    }

                } else {
                    $display = PRODS . $productManager->getAll($db);
                }

            } elseif ($params[1] === CAT) {
                $display = CATS;
                $categoryManager = new CategoryManager($db);

                if (isset($params[2])) {

                    if (is_numeric($params[2])) {
                        $result = $categoryManager->findById($db, (int)$params[2]);
                        $display = $result ? PRODS . $result : NO_EXIST_ID;
                    } else {
                        $display = NO_CORRECT_PARAM_NEED_ID;
                    }

                } else {
                    $display = $categoryManager->getAll($db);
                }

            } elseif ($params[1] === MAG) {
                $display = MAGS;
                $storeManager = new StoreManager($db);

                if (isset($params[2])) {

                    if (is_numeric($params[2])) {
                        $result = $storeManager->findById($db, (int)$params[2]);
                        $display = $result ? PRODS . $result : NO_EXIST_ID;
                    } else {
                        $display = NO_CORRECT_PARAM_NEED_ID;
                    }

                } else {
                    $display = $storeManager->getAll($db);
                }

            }
            break;
        default:
    }
    echo $display;
}
