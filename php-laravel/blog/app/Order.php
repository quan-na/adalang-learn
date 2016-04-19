<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    // declare relation ship
    public function customer() {
        return $this->belongsTo('App\Customer');
    }
}
