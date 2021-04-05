<?php

class StoreManager 
{
    /**
     * @var PDO
     */
    private $db;

    /**
     * StoreManager constructor.
     * @param PDO $db
     */
    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function getAll($db) {
        $results = $db->query("SELECT * FROM store")->fetchAll();
        header("content-type", "application/json");
        $stores = [];
        foreach($results as $row) {
            $stores[] = $row;
        }
        return json_encode($stores);
    }

    public function findById($db, $id) {
        $result = $db->query("SELECT * FROM store WHERE id = " . $id)->fetch();
        header("content-type", "application/json");
        return json_encode($result);
    }

    public function create($db, $store) {
        $name = $store->name;
        $result = $db->prepare("INSERT INTO store (name) VALUES (:name)")
            ->execute([':name' => $name]);
        return json_encode($result);
    }

    public function update($db, $store) {
        $id = (int) $store->id;
        $name = $store->name;
        $result = $db->prepare("UPDATE store SET name = :name WHERE id = :id")
            ->execute([':name' => $name, ':id' => $id]);
        return json_encode($result);
    }

    public function delete($db, $id) {
        $result = $db->prepare("DELETE FROM store WHERE id = :id")->execute([':id' => $id]);
        return json_encode($result);
    }

}
