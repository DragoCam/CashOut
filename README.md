# CashOut Plugin for Minecraft

**CashOut** is a Minecraft plugin that allows players to withdraw and deposit money using custom banknotes. It provides an easy way for players to manage their virtual currency and offers full support for custom messages and multi-language setups.

## Features

- **Custom Messages**: Fully supports custom messages in all languages.
- **Custom Note Item**: Allows you to configure the appearance of the banknote (e.g., material, name, lore).
- **Withdraw Money**: Players can withdraw money from their wallet and receive it as a note.
- **Deposit Money**: Players can deposit their notes back into their wallet.
- **Reload Command**: The ability to reload the plugin without restarting the server.

## Planned Updates

- **Integration with ItemsAdder Plugin**: Upcoming update to support custom items using the ItemsAdder plugin.

## Dependencies

- **Vault**: Required for economy integration.
- **ItemsAdder (Optional)**: For custom items support (optional, but recommended for advanced configurations).

## Commands & Permissions

### /withdraw
- **Description**: Withdraw money as a custom note.
- **Permission**: `CashOut.withdraw`

### /deposit
- **Description**: Deposit a note into your wallet.
- **Permission**: `CashOut.deposit`

### /reload
- **Description**: Reload the plugin configuration.
- **Permission**: `CashOut.reload`

## Note Configuration

Customize the appearance and details of your banknotes with the following configuration:

```yaml
note:
  material: PAPER              # The item material for the note.
  data: 0                      # The item data value (use 0 for default).
  name: "&lBanknot"            # The name of the note (supports color codes).
  lore:
    - "$[money]"               # The money amount displayed on the note.
    - "Wypłacony przez [player]" # The player who withdrew the money.
