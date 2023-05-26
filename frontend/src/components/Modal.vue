<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="290">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on"> Open Dialog </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5"> {{ message }} </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false"> Agree </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      message: " ",
      dialog: false,
    };
  },
  methods: {
    buttonEvent() {
      this.$emit("close");
      dialog = false;
    },
  },
};
</script>
